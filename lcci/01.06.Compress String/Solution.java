class Solution {
    public String compressString(String S) {
        if (S == null || S.length() < 2) {
            return S;
        }
        char[] chars = S.toCharArray();
        int p = 0, q = 1, n = chars.length;
        StringBuilder sb = new StringBuilder();
        while (q < n) {
            if (chars[p] != chars[q]) {
                sb.append(chars[p]).append(q - p);
                p = q;
            }
            q += 1;
        }
        sb.append(chars[p]).append(q - p);
        String res = sb.toString();
        return res.length() < n ? res : S;
    }
}