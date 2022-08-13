class Solution {
public:
    string rankTeams(vector<string>& votes) {
        unordered_map<char, vector<int>> counter;
        int n = votes[0].size();
        for (auto& vote : votes) {
            for (int i = 0; i < n; ++i) {
                char v = vote[i];
                counter[v].resize(n);
                ++counter[v][i];
            }
        }
        string ans = votes[0];
        sort(ans.begin(), ans.end(), [&](char a, char b) {
            return counter[a] > counter[b] || (counter[a] == counter[b] && a < b);
        });
        return ans;
    }
};