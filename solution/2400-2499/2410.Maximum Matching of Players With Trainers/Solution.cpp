class Solution {
public:
    int matchPlayersAndTrainers(vector<int>& players, vector<int>& trainers) {
        sort(players.begin(), players.end());
        sort(trainers.begin(), trainers.end());
        int ans = 0, j = 0;
        for (int p : players) {
            while (j < trainers.size() && trainers[j] < p) {
                ++j;
            }
            if (j < trainers.size()) {
                ++ans;
                ++j;
            }
        }
        return ans;
    }
};