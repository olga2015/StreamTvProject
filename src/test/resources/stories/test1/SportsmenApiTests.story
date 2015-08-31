
Meta:
@runScenario

Scenario: CRUD sportsman
Given create sportsman
When read sportsman
Then verify read name equals to 1
Then update sportsman and verify success answer:
| mname  | dob        |
| Auto15 | 03-03-1989 |
Then delete sportsman and verify success answer from server