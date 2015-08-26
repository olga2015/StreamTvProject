ChangeLog:
Added test


Scenario: CRUD sportsmen
Given User opens Login Page and login to the base page
When add new sportsmen with data:
| lastName     | firstName | middleName | birthDate  | Region1       | FST1    | trainer1       | style | age    | year | cardAvailability  |
| Automation01 | Auto01    | Automiddle | 07-07-1987 | Kyrovogradska | Dinamo  | TheBestTrainer |  FS   | Junior | 2015 | Produced          |
Then user finds added sportsmen and checks saved data