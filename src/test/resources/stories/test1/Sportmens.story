ChangeLog:
26/08/2015  Added test 

Meta:
@runScenario

Scenario: Precondition steps
Given User opens Login Page and login to the base page
When delete sportsman with name Automation01 Auto01 Automiddle

Scenario: 1_1 Add new sportsman and verify saved data
Given User opens Login Page and login to the base page
When add new sportsmen with data:
| lastName     | firstName | middleName | birthDate  | Region1       | FST1    | trainer1       | style | age    | year | cardAvailability  |
| Automation01 | Auto01    | Automiddle | 07-07-1987 | Kyrovogradska | Dinamo  | TheBestTrainer |  FS   | Junior | 2015 | Produced          |
Then user finds added sportsmen and checks saved data

Scenario: 1_2 Update values for added sportsman
Given User opens Login Page and login to the base page
When find current sportsman and edit with values:
| Region1 | FST1  | style | age    | year | cardAvailability  |
| Kyivska | Kolos |  GR   | Senior | 2016 | Recieved          |
Then user finds added sportsmen and checks saved data

Scenario: 1_3 Update values for added sportsman
Given User opens Login Page and login to the base page
When find current sportsman and delete profile

Scenario: 2_1 Find sportsmen with defined filters and verify searched data
Given User opens Login Page and login to the base page
When finds data with filters:
| Region | Fst    |
| Odeska | Dinamo |
Then verify data has been correctly selected

Scenario: 3_1 Upload sportsman's photo and verify it
Given User opens Login Page and login to the base page
When add new sportsmen with data:
| lastName     | firstName | middleName | birthDate  | Region1       | FST1    | style | age    | year |
| Automation01 | Auto01    | Automiddle | 07-07-1987 | Kyrovogradska | Dinamo  |  FS   | Junior | 2015 |
And finds sportsman and uploads photo with name picture.jpg
Then verify image has been uploded on the Page

Scenario: 3_2 Upload attachment, verify it and delete
Given User opens Login Page and login to the base page
When finds sportsman and uploads attachment with name 112233.txt
Then verify correct attachment was uploaded
When deletes attachment

Scenario: Postcondition steps
Given User opens Login Page and login to the base page
When delete sportsman with name Automation01 Auto01 Automiddle