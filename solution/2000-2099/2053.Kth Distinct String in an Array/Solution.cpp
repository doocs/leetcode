class Solution {
public:
    string kthDistinct(vector<string>& arr, int k) {
        unordered_map<string, int> cnt;
        for (const auto& s : arr) {
            ++cnt[s];
        }
        for (const auto& s : arr) {
            if (cnt[s] == 1 && --k == 0) {
                return s;
            }
        }
        return "";
    }
};
