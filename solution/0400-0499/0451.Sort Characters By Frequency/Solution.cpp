class Solution {
public:
    string frequencySort(string s) {
        unordered_map<char, int> cnt;
        for (char& c : s) {
            ++cnt[c];
        }
        vector<char> cs;
        for (auto& [c, _] : cnt) {
            cs.push_back(c);
        }
        sort(cs.begin(), cs.end(), [&](char& a, char& b) {
            return cnt[a] > cnt[b];
        });
        string ans;
        for (char& c : cs) {
            ans += string(cnt[c], c);
        }
        return ans;
    }
};