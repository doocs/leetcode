class Solution {
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int n = s.length();
        var d = new int[n];
        Arrays.fill(d, -1);
        for (int k = 0; k < indices.length; ++k) {
            int i = indices[k];
            if (s.startsWith(sources[k], i)) {
                d[i] = k;
            }
        }
        var ans = new StringBuilder();
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