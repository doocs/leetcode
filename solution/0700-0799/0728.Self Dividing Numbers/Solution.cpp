class Solution {
 public:
    int div(int num){

        int temp = num, r;

        while( temp > 0 ){

            r = temp % 10;

            if( r == 0 || num % r != 0){
                return 0;
            }

            temp /= 10;
        }

        return 1;
    }

    vector<int> selfDividingNumbers(int left, int right) {

        vector<int> ret;

        for(int i = left; i <= right; i++){
            if( div(i) ){
                ret.push_back(i);
            }
        }

        return ret;
    }
};
