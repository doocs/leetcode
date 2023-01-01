class Solution {
public:
    int findNumbers(vector<int>& nums) {
        int ans = 0;
        for (int& v : nums) {
            ans += to_string(v).size() % 2 == 0;
        }
        return ans;
    }
};