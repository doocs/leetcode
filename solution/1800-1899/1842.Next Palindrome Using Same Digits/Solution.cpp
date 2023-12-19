class Solution {
public:
    string nextPalindrome(string num) {
        int n = num.size();
        string nums = num.substr(0, n / 2);
        if (!next_permutation(begin(nums), end(nums))) {
            return "";
        }
        for (int i = 0; i < n / 2; ++i) {
            num[i] = nums[i];
            num[n - i - 1] = nums[i];
        }
        return num;
    }
};