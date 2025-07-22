class Solution {
    public String rankTeams(String[] votes) {
        int m = votes[0].length();
        int[][] cnt = new int[26][m + 1];
        for (var vote : votes) {
            for (int i = 0; i < m; ++i) {
                ++cnt[vote.charAt(i) - 'A'][i];
            }
        }
        Character[] s = new Character[m];
        for (int i = 0; i < m; ++i) {
            s[i] = votes[0].charAt(i);
        }
        Arrays.sort(s, (a, b) -> {
            int i = a - 'A', j = b - 'A';
            for (int k = 0; k < m; ++k) {
                if (cnt[i][k] != cnt[j][k]) {
                    return cnt[j][k] - cnt[i][k];
                }
            }
            return a - b;
        });
        StringBuilder ans = new StringBuilder();
        for (var c : s) {
            ans.append(c);
        }
        return ans.toString();
    }
}
