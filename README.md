# Randomized-Subset-Sum
An improved solution to existing randomized approximation approach to subset sum problem
Existing Randomized approach has a runtime O(n*log(n)
My improvement to the problem gives a linear runtime O(n) with some trade off of approximation error 
As a future work some improvement can be done for reducing the approximation error

Existing randomized approach - http://web.stevens.edu/algebraic/Files/SubsetSum/przydatek99fast.pdf
The algorithm has two phases
First phase - solution vector is  randomly chosen
i.e a number ai  is examined in random order and each ‘ai’ is inserted to the current solution if it is smaller than the difference between S and the sum of current solution
Second phase - local optimization  
All the numbers in the solution vector found in first phase are examined
For each number ai in the solution vector we search for the largest number not in the current solution which would reduce the error when taken into solution instead of ai
The algorithm has one or more independent trials with a new solution vector for each trial and the best among all trials is taken as the solution


Proposed Improvement
Second Phase – sorting is leading to O(n*log(n)) running time
Find the smallest and largest number from the randomly chosen solution vector. 
Smallest Number - as 
Largest Number – al
Eliminate the values 
< as 
> al + 𝛿(𝑥)
with 𝛿(𝑥) being the error i.e difference between the sum of numbers in the solution vector and ‘S’ the sum
If Size ≤ ∜𝑛^3 , sort those elements and do the same as the existing approach
If not, then we randomly select a number as a replacement since each number is a potential element of reducing the error. 
