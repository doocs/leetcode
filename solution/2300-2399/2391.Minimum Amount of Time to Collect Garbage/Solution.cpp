class Solution {
public:
    int garbageCollection(vector<string>& garbage, vector<int>& travel) {
        int n = garbage.size(), m = travel.size();
        int last[26]{};
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += garbage[i].size();
            for (char& c : garbage[i]) {
                last[c - 'A'] = i;
            }
        }
        int s[m + 1];
        s[0] = 0;
        for (int i = 1; i <= m; ++i) {
            s[i] = s[i - 1] + travel[i - 1];
        }
        for (int i : last) {
            ans += s[i];
        }
        return ans;
    }
};