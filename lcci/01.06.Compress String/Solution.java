class Solution {
    public String compressString(String S) {
        int n = S.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && S.charAt(j) == S.charAt(i)) {
                ++j;
            }
            sb.append(S.charAt(i)).append(j - i);
            i = j;
        }
        String t = sb.toString();
        return t.length() < n ? t : S;
    }
}