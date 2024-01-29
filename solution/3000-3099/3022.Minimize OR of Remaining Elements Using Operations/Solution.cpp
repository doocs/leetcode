class Solution {
public:
    int minOrAfterOperations(vector<int>& nums, int k) {
        int ans = 0, rans = 0;
        for (int i = 29; i >= 0; i--) {
            int test = ans + (1 << i);
            int cnt = 0;
            int val = 0;
            for (auto it : nums) {
                if (val == 0) {
                    val = test & it;
                }
                else {
                    val &= test & it;
                }
                if (val) {
                    cnt++;
                }
            }
            if (cnt > k) {
                rans += (1 << i);
            }
            else{
                ans += (1 << i);
            }
        }
        return rans;
    }
};
