class Solution {
public:
    bool checkDistances(string s, vector<int>& distance) {
        vector<int> d(26);
        for (int i = 0; i < s.size(); ++i) {
            int j = s[i] - 'a';
            if (d[j] && i - d[j] != distance[j]) {
                return false;
            }
            d[j] = i + 1;
        }
        return true;
    }
};