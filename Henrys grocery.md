# Task
​
_We do **not** expect you to spend more than 2 to 3 hours of your time. Please do not focus on providing a complete solution. We'd like you to talk us through your solution (using screen sharing), and have discussions on topics related to [software engineering](https://en.wikipedia.org/wiki/Software_engineering) and [agile software development](https://en.wikipedia.org/wiki/Agile_software_development). Create a fork and have fun! By the way, we're mostly using Java._
​

A local shop, Henry’s Grocery, has asked you to author an IT solution for them to price up a basket of shopping for their customers. Henry’s Grocery currently only stocks four items and has two offers. These are as follows:
​
- _Stock Items_
        
| `product` | `unit` | `cost` |
|:----------|:------:|:------:|
| soup      |  tin   |  0.65  |
| bread     |  loaf  |  0.80  |
| milk      | bottle |  1.30  |
| apples    | single |  0.10  |


- _Discounts_
 
| `the offer`                                           |   `valid from`    |              `valid to`              | 
|:------------------------------------------------------|:-----------------:|:------------------------------------:|    
| Buy 2 tins of soup and get a loaf of bread half price |     yesterday     |              for 7 days              |
| Apples have a 10% discount                            | from 3 days hence | until the end of the following month |


- _Inputs_
  - All basket items added via the command prompt.
- _Outputs_
  -	All outputs must print to the command line.


   
- _Tests_
  - Price a basket containing: 3 tins of soup and 2 loaves of bread, bought today, 
    - Expected total cost = 3.15;
  - Price a basket containing: 6 apples and a bottle of milk, bought today, 
    - Expected total cost = 1.90;
  - Price a basket containing: 6 apples and a bottle of milk, bought in 5 days time,
    - Expected total cost = 1.84;
  - Price a basket containing: 3 apples, 2 tins of soup and a loaf of bread, bought in 5 days time,
    - Expected total cost = 1.97.


## Hints
​
- Use Java _ideally JDK 17_
- Keep it simple
- Add test cases, ideally using [TDD](https://en.wikipedia.org/wiki/Test-driven_development)
- Use version control, preferably [Git](https://en.wikipedia.org/wiki/Git)
- Make small steps and have small commits
​
## Having an urge to show off?
​
- Don't focus on the final solution. Focus on each and every step (commit) you're making instead, along with the sequence of those steps. How does each step and series of steps add value to your solution?
- How would you enhance your solution so that the store owner could add and remove products and/or offers?
- What (code) metrics helped you make decisions along the way?










