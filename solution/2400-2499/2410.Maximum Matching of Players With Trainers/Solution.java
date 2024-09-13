class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int m = players.length, n = trainers.length;
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
}
