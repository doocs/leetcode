class Solution {
public:
    vector<int> mostVisited(int n, vector<int>& rounds) {
        int m = rounds.size() - 1;
        vector<int> ans;
        if (rounds[0] <= rounds[m]) {
            for (int i = rounds[0]; i <= rounds[m]; ++i) ans.push_back(i);
        } else {
            for (int i = 1; i <= rounds[m]; ++i) ans.push_back(i);
            for (int i = rounds[0]; i <= n; ++i) ans.push_back(i);
        }
        return ans;
    }
};