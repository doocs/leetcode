class Solution {
    public String rankTeams(String[] votes) {
        int n = votes[0].length();
        int[][] cnt = new int[26][n];
        for (var vote : votes) {
            for (int i = 0; i < n; ++i) {
                cnt[vote.charAt(i) - 'A'][i]++;
            }
        }
        Character[] cs = new Character[n];
        for (int i = 0; i < n; ++i) {
            cs[i] = votes[0].charAt(i);
        }
        Arrays.sort(cs, (a, b) -> {
            int i = a - 'A', j = b - 'A';
            for (int k = 0; k < n; ++k) {
                int d = cnt[i][k] - cnt[j][k];
                if (d != 0) {
                    return d > 0 ? -1 : 1;
                }
            }
            return a - b;
        });
        StringBuilder ans = new StringBuilder();
        for (char c : cs) {
            ans.append(c);
        }
        return ans.toString();
    }
}