class Solution {
public:
    vector<string> fizzBuzz(int n) {
        vector<string> Ret(n);
        
        for(int i=1; i<=n; i++){
            
            Ret[i-1] = (i%15 == 0) ? "FizzBuzz" : (i%3 == 0) ? "Fizz" : (i%5 == 0) ? "Buzz": to_string(i);
            
        }
        
        return Ret;
    }
};
