class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int j = 0, m = players.length;
        for (int t : trainers) {
            if (j < m && players[j] <= t) {
                ++j;
            }
        }
        return j;
    }
}
