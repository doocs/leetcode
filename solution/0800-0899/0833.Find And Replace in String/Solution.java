class Solution {
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int n = s.length();
        int[] d = new int[n];
        Arrays.fill(d, -1);
        for (int i = 0; i < indices.length; ++i) {
            int j = indices[i];
            String source = sources[i];
            if (s.substring(j, Math.min(n, j + source.length())).equals(source)) {
                d[j] = i;
            }
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n;) {
            if (d[i] >= 0) {
                ans.append(targets[d[i]]);
                i += sources[d[i]].length();
            } else {
                ans.append(s.charAt(i++));
            }
        }
        return ans.toString();
    }
}