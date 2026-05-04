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