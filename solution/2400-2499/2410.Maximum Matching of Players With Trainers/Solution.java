class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int ans = 0;
        int j = 0;
        for (int p : players) {
            while (j < trainers.length && trainers[j] < p) {
                ++j;
            }
            if (j < trainers.length) {
                ++ans;
                ++j;
            }
        }
        return ans;
    }
}