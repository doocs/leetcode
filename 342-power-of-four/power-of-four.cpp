class Solution {
public:
    bool isPowerOfFour(int n) {
                if(n == 0) return false;
        if(n == 1) return true;
        return (n%4==0)?isPowerOfFour(n/4):false;
        
    }
};