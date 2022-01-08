# Local Search Comparison
Lab for local searches impl

## Repeated local search
1. randomly choose starting point;
2. if condition met, save solution and rerun from step 1.

## Iterated Local Search
1. start with random solution.
2. while condition not met: 
    1. find best local solution;
    2. perturb best local solution;    
    
## Guided Local Search
1. build initial Solution with shuffle;
2. initialize Penalties;
3. while condition not met:
    1. calculate augmented objective function as: h = f(x) + \lambda * \sum p_i * I_i;   
       where h is augmented function, f(x) - cost function, \lambda - metaheuristic parameter, p_i - penalty for i-feature, I_i - indicator, if i-feature exists or not.
    2. run local search, based on previous solution;
    3. calculate utility for each feature as: util_i = I_i * c_i / (1 + p_i);  
       where c_i - cost of feature i in objective function.
    4. increase penalty for feature with maximum utility;

Note: we assume, that feature i means, that at place i we put i factory.
So it's cost might be calculated as follows:  
`
    for (int j = 0; j < n; j++) {  
        costOfFeatureI += p.distance[i][j] * p.flow[location[i]][location[j]];  
    }  
`

---
## Final Table
| Instance | BKV      | RLS      | ILS      | GLS      |
| -------- | -------- | -------- | -------- | -------- |
| Tai20a   | 703482   | 773936   | 760740   | 757400   |
| Tai40a   | 3139370  | 3559148  | 3521372  | 3386346  |
| Tai60a   | 7205962  | 8219922  | 8167046  | 7805588  |
| Tai80a   | 13499184 | 15145328 | 15206250 | 14475326 |
| Tai100a  | 21052466 | 23471174 | 23481320 | 22414046 |

---
BKV was taken from [here](https://books.google.ru/books?id=ZCu7BQAAQBAJ&pg=PA34&lpg=PA34&dq=tai20a+result&source=bl&ots=q_6tEmUoML&sig=H6st6LU13rwYhVhXH_jkfbPUymE&hl=ru&sa=X&ved=0ahUKEwjuw52V7dzWAhXqKJoKHTOtCWUQ6AEIMzAC#v=onepage&q=tai20a%20result&f=false)
