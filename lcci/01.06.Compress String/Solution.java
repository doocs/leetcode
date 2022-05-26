class Solution {
    public String compressString(String S) {
        int n;
        if (S == null || (n = S.length()) < 2) {
            return S;
        }
        int p = 0, q = 1;
        StringBuilder sb = new StringBuilder();
        while (q < n) {
            if (S.charAt(p) != S.charAt(q)) {
                sb.append(S.charAt(p)).append(q - p);
                p = q;
            }
            ++q;
        }
        sb.append(S.charAt(p)).append(q - p);
        String res = sb.toString();
        return res.length() < n ? res : S;
    }
}