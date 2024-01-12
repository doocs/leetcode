class Solution {
public:
    int countWords(vector<string>& words1, vector<string>& words2) {
        unordered_map<string, int> cnt1;
        unordered_map<string, int> cnt2;
        for (auto& w : words1) {
            ++cnt1[w];
        }
        for (auto& w : words2) {
            ++cnt2[w];
        }
        int ans = 0;
        for (auto& [w, v] : cnt1) {
            ans += v == 1 && cnt2[w] == 1;
        }
        return ans;
    }
};