class Solution {
public:
    string findContestMatch(int n) {
        vector<string> team(n);
        for (int i = 0; i < n; ++i) team[i] = to_string(i + 1);
        for (; n > 1; n >>= 1) {
            for (int i = 0; i<n> > 1; ++i) {
                team[i] = "(" + team[i] + "," + team[n - 1 - i] + ")";
            }
        }
        return team[0];
    }
};