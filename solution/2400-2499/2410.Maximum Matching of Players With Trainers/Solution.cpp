class Solution {
public:
    int matchPlayersAndTrainers(vector<int>& players, vector<int>& trainers) {
        ranges::sort(players);
        ranges::sort(trainers);
        int m = players.size(), n = trainers.size();
        for (int i = 0, j = 0; i < m; ++i, ++j) {
            while (j < n && trainers[j] < players[i]) {
                ++j;
            }
            if (j == n) {
                return i;
            }
        }
        return m;
    }
};
