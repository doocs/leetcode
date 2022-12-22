class Solution {
public:
    int finalValueAfterOperations(vector<string>& operations) {
        int ans = 0;
        for (auto& s : operations) ans += (s[1] == '+' ? 1 : -1);
        return ans;
    }
};