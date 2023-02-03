class Solution {
public:
    string minNumber(vector<int>& nums) {
        vector<string> arr;
        for (int& x : nums) {
            arr.emplace_back(to_string(x));
        }
        sort(arr.begin(), arr.end(), [](const auto& a, const auto& b) {
            return a + b < b + a;
        });
        string ans;
        for (auto& x : arr) {
            ans += x;
        }
        return ans;
    }
};