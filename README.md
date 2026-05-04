### TP KISS

1. **Que pensez-vous de l'architecture de ce code au regard du besoin à implémenter ?**

Le besoin est de calculer le montant d'une commande en appliquant une remise de 10%  pour le menu adventurer.

L'architecture mise en place est fortement sur ingéniérée. Elle empile trois design patterns pour un besoin basique :

- Strategy (`ICalculationStrategy`)
- Decorator (`AbstractOrderCalculatorDecorator`, `AdventurerMenuDiscountDecorator`)
- Factory (`DiscountStrategyFactory`)

Au lieu de simplifier la compréhension, cette architecture la complexifie inutilement. Il faut naviguer dans 4 fichiers différents pour comprendre qu'on fait simplement un `montant * 0.90`. C'est une violation du principe KISS qui recommande d'éviter la complexité non nécessaire.

2. **Chaque fichier présent dans ce package vous semble-t-il justifié ?**

Non, la grande majorité de ces abstractions sont injustifiées au vu de la simplicité du calcul.

- `ICalculationStrategy.java` : Injustifié. Une interface n'est utile que si l'on a réellement de multiples manières fondamentalement différentes de calculer le montant. Ici, une simple fonction suffit.
- `AbstractOrderCalculatorDecorator.java` : Totalement injustifié. Le décorateur est utile pour combiner dynamiquement de multiples comportements. Si on ne cherche qu'à appliquer une seule remise à la fois, c'est du code mort.
- `AdventurerMenuDiscountDecorator.java` : Injustifié. Une classe entière pour encapsuler la logique `montant * 0.90` est superflue.
- `DiscountStrategyFactory.java` : Injustifié. L'utilisation d'une fabrique avec un instanciateur dynamique pour retourner presque tout le temps l'objet de base (NONE) ou un pauvre décorateur rajoute une couche d'indirection inutile.

3. **Si vous deviez repartir de zéro pour implémenter cette fonctionnalité, comment structureriez-vous votre solution ?**

En appliquant le principe KISS, je supprimerais tous les Design Patterns (Strategy, Decorator, Factory) pour les remplacer par un simple enum et un service classique.

- **Lisibilité stricte :** N'importe quel développeur comprendra ce code du premier coup d'œil.
- **Maintenance aisée :** S'il faut ajouter une "Remise Magicien", il suffira d'ajouter une ligne dans l'Enum `MAGICIAN_MENU(0.8)`. Pas besoin de créer 2 nouvelles classes et de modifier une Factory.
- **Moins de fichiers :** On passe de 4-5 classes abstraites et interfaces à seulement l'essentiel, ce qui réduit la charge cognitive du projet.

### TP DRY

1. **Que pensez-vous de la qualité de ce code ?**

On observe une  **duplication de logique métier** à travers les différentes méthodes de calcul :

- Le calcul de base `req.getPrice() * req.getQuantity()` est répété trois fois (dans `/warrior`, `/mage`, `/rogue`).
- Le calcul de la taxe du roi `base * 0.05` est répété deux fois.

Bien que le code soit fonctionnel, il mélange l'exposition des API (le rôle du `Controller`) avec la logique métier (le calcul du prix final).

2. **Évolution du taux de taxe ou de la formule de calcul : Implications et conséquences**

Si la taxe du roi passe de 5% à 7% (`0.07`), ou si l'on décide que le calcul de base prend désormais en compte des frais de service par article, voici ce que cela implique :

- **Modifications multiples** : On devra manuellement scruter chaque méthode pour trouver où ce calcul spécifique est fait et le modifier (2 fois pour la taxe, 3 fois pour le calcul de base).
- **Risque d'erreurs (régressions)** : Si l'on oublie de modifier la taxe du roi dans la méthode `calculateRogue` alors qu'on l'a modifiée dans `calculateWarrior`, le système deviendra incohérent.
- **Maintenance pénible** : Cela devient insatisfaisant car l'ajout de nouvelles classes de personnages exigerait du "copier-coller", multipliant ainsi la charge de maintenance.

3. **Comment refactoreriez-vous ce code pour le rendre plus maintenable ?**

Pour rendre ce code maintenable, il faut extraire et centraliser la logique commune.

Les calculs récurrents doivent être isolés. Puisqu'il s'agit d'une petite API, extraire des méthodes privées est suffisant dans un premier temps. Si la logique devait grossir ou s'appliquer ailleurs, un vrai Service métier (`PaymentService`) serait à créer.

1. **Suppression de la duplication (DRY)** : Le calcul de base `getPrice() * getQuantity()` n'est défini qu'une seule fois. Si cette formule change, je ne modifie que la méthode `calculateBase`.
2. **Centralisation de la Taxe du Roi** : L'extraction en constante et en méthode garantit que si la taxe change, on ne modifie la valeur qu'à **un seul endroit**. Tous les personnages affectés seront mis à jour mécaniquement.
3. **Lisibilité** : Les actions des Endpoint de l'API deviennent très concises, exprimant directement la "formule" finale pour chaque classe, sans noyer la vue sous les calculs intermédiaires.

### TP YAGNI

1. **Constatations et appréciation globale du code livré**

- On retrouve des services fantômes qui jettent des exceptions `UnsupportedOperationException`
- Des méthodes vides ou qui renvoient des valeurs codées en dur (`WeatherIntegrationService`, points de terminaison `/vip` et `/weather-check` dans le contrôleur).
- Un modèle de données (`Reservation`) surchargé d'attributs qui n'ont probablement aucun lien avec le besoin métier actuel.

L'intention première (faire et lister des réservations) est noyée sous une montagne de fonctionnalités purement anticipées et non fonctionnelles.

2. **Utilité et justification des fichiers et attributs**

La très grande majorité de ce qui est présent **n'est pas justifiée**, car rien n'est effectivement achevé ni utilisé par la logique métier centrale :

- **`CryptoPaymentGateway.java`** et **`WeatherIntegrationService.java`** : Totalement inutiles. Ces classes n'implémentent aucune vraie logique et ne sont même pas appelées correctement. Elles alourdissent le projet.
- **`INotificationService.java`** : Des méthodes (`sendPalantirAlert`, `sendSmokeSignal`) qui relèvent de la pure fantaisie anticipative et n'ont aucune implémentation.
- **`Reservation.java`**: Seuls les champs de base (`id`, `groupName`, `arrivalTime`, `guestCount`) sont justifiés pour une réservation standard. Tout le reste (`cryptoCurrency`, `weatherForecast`, `palantirChannelId`, `vipTier`, etc.) est du "bruit" qui complexifie inutilement la manipulation de l'objet et son éventuelle sauvegarde en base de données.
- **`ReservationController.java`** : Les routes `/vip` et `/weather-check` sont injustifiées. Elles exposent aux clients de l'API des endpoints qui ne font strictement rien d'utile.

3. **Que répondre à un développeur adepte du "plus on anticipe, mieux c'est" ?**

L'anticipation excessive coûte très cher en développement logiciel, pour plusieurs raisons visibles directement dans ce code :

1. **La charge de maintenance (Dette technique) :** Chaque ligne de code ajoutée doit être lue, comprise, potentiellement documentée et migrée lors des montées de version. Ici, un nouveau développeur devra perdre du temps à comprendre à quoi sert `palantirChannelId` dans le modèle `Reservation`, pour finalement se rendre compte que ça ne sert à rien.
2. **Le risque de bugs :** La classe `CryptoPaymentGateway` lance une `UnsupportedOperationException`. Si, par accident, un autre morceau du code finit par appeler ce service non testé, cela fera crasher l'application en production.
3. **Le principe de réalité :** Le besoin métier évolue constamment. Si l'on anticipe aujourd'hui le paiement par GoblinCoin, il y a de fortes chances que dans 6 mois, lorsque le client voudra réellement un système de paiement, il choisisse la carte bancaire classique ou une autre cryptomonnaie. Tout le code spéculatif devra alors être jeté et réécrit.

### TP SOLID

 1. **Appréciation globale de la base de code**

Ce code est un "anti-pattern". On constate qu'il enfreint la quasi-totalité des principes de l'architecture logicielle moderne. La classe `TavernManager` est une "God Object" (Classe Dieu) qui sait tout faire et mélange de nombreux rôles, tandis que le reste du modèle est mal conçu, avec des abstractions cassées (exceptions jetées au lieu de renvoyer un booléen) et des dépendances dures créées directement au milieu des méthodes métier. Le code est rigide, fragile, difficilement testable et fortement couplé.

2. **Évaluation des 5 principes SOLID**

- **S - NON RESPECTÉ**
    - **Justification :** Le contrôleur `TavernManager` fait le routage HTTP, vérifie et met à jour le stock (logique d'inventaire), calcule le total et les taxes (logique métier financière), gère l'heure pour les majorations, sauvegarde des notifications (accès aux données) via un composant SQL instancié en baldur, et s'occupe même de sysout et de stocker des variables "en mémoire" comme si c'était une base de données. Il a au moins 6 raisons de changer.
- **O - NON RESPECTÉ **
    - **Justification :** Dans `TavernManager`, le calcul des taxes inclut des conditions strictes (`if` à 22h, `if` Samedi). Si on ajoute une taxe le lundi, ou une taxe sur un article précis, il faudra modifier ce code existant qui gère déjà le routage HTTP au lieu de pouvoir simplement étendre ou injecter une nouvelle stratégie de taxe (ce qui requerrait une modification de classe et contredirait l'idée du "fermé à la modification").
- **L - NON RESPECTÉ **
    - **Justification :** Ce principe est clairement violé par `PoisonousDrink`. Une boisson empoisonnée hérite de `ConsumableItem`, mais au lieu de redéfinir `isSafeToConsume()` pour renvoyer `false` (conformément à l'attitude attendue d'un consommable), la classe déclenche une `RuntimeException`. Par conséquent, le polymorphisme casse le système existant ; le parcours d'une liste de `ConsumableItem` fera planter l'application à cause de cette déviation de comportement inattendue.
- **I - NON RESPECTÉ**
    - **Justification :** L'interface `IItemActions` est trop "large" (fat interface). Elle oblige l'implémentation de méthodes comme `cook()`, `pourIntoMug()`, `roast()`, `ferment()` et `distill()`. Une classe `Bière` sera contrainte de fournir un `.cook()` dénué de sens, et une classe `Steak` aura un `.distill()`. Les rôles d'un cuisinier et d'un brasseur devaient être séparés dans de plus petites interfaces.
- **D - NON RESPECTÉ**
    - **Justification :** Le `TavernManager` instancie explicitement une base de haut niveau dépendante d'un composant local: `SqlNotificationRepository repo = new SqlNotificationRepository();`. Le contrôleur dépend des implémentations (le mot _SQL_ en est le signe évident) au lieu d'une interface commune telle que `INotificationRepository`. Changer ce dépôt vers une solution dans le cloud ou vers un fichier impliquerait de recompiler et modifier le code de calcul métier.

### 3. Difficultés anticipées lors de l'évolution du code

Généralement, pour étendre ce code, de nombreux conflits surgiront.

- **Ajout d'un nouveau type de notification (Email, Cloud) :** On sera forcé de supprimer explicitement ce `new SqlNotification...()` et d'éditer la logique des commandes qui en fait n'est en rien liée à de la notification, car le système s'articule autour des implémentations.
- **Ajouter une promotion ou des taxes saisonnières :** Il va falloir rajouter de la logique de calcul conditionnel, allongeant considérablement la méthode dans `processOrder()` et complexifier ce qui est aujourd'hui un service HTTP, entraînant une illisibilité croissante et des tests de routage HTTP et règles financières croisés pénibles.
- **Faire de la gestion de stock avancée :** En utilisant un simple dictionnaire ou cache local dans le contrôleur, ce composant est à usage unique qui ne permet ni partage avec différents composants, ne survivrait pas à une redondance serveur, et les actions comme des approvisionnements ne seraient pas gérables.