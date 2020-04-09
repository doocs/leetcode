class Solution {
 public:

    vector<int> primes;

    int isPrime(int num){

        int i = 1;
        while( i < primes.size() && sqrt(num) >= primes[i] ){
            if( num % primes[i] == 0 ) return 0;
            i++;
        }
        return 1;
    }

    int countPrimes(int n) {

        if( n <= 2 ) return 0;

        primes.push_back(2);

        for(int i = 3; i < n; i += 2){
            if( isPrime(i) )
                primes.push_back(i);
        }

        return primes.size();
    }
};
