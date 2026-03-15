class Solution {
public:
    int firstUniqueEven(vector<int>& nums) {
        int cnt[101]{};
        for (int x : nums) {
            ++cnt[x];
        }
        for (int x : nums) {
            if (x % 2 == 0 && cnt[x] == 1) {
                return x;
            }
        }
        return -1;
    }
};
