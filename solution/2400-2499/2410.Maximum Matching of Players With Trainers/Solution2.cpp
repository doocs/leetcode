class Solution {
public:
    int matchPlayersAndTrainers(vector<int>& players, vector<int>& trainers) {
        ranges::sort(players);
        ranges::sort(trainers);
        int j = 0, m = players.size();
        for (int t : trainers) {
            if (j < m && players[j] <= t) {
                ++j;
            }
        }
        return j;
    }
};
