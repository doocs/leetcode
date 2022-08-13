class Solution {
public:
    vector<int> singleNumbers(vector<int>& nums) {
        int res = 0;
        for(int x : nums) {
            res ^= x;
        }
        int div = 1;
        while(!(div & res)) {
            div <<= 1;
        }
        int a = 0,b = 0;
        for(int x : nums) {
            if(x & div) {
                a ^= x;
            } else {
                b ^= x;
            }
        }
        return vector<int> {a,b};
    }
};