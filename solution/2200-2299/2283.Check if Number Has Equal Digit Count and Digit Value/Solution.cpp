class Solution {
public:
    bool digitCount(string num) {
        vector<int> cnt(10);
        for (char& c : num) ++cnt[c - '0'];
        for (int i = 0; i < num.size(); ++i) {
            int v = num[i] - '0';
            if (cnt[i] != v) return false;
        }
        return true;
    }
};