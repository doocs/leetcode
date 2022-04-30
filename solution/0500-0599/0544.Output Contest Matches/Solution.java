class Solution {
    public String findContestMatch(int n) {
        String[] team = new String[n];
        for (int i = 0; i < n; ++i) {
            team[i] = "" + (i + 1);
        }
        for (; n > 1; n /= 2) {
            for (int i = 0; i < n / 2; ++i) {
                team[i] = "(" + team[i] + "," + team[n - 1 - i] + ")";
            }
        }
        return team[0];
    }
}