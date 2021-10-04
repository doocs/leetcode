class Solution {
    public String licenseKeyFormatting(String s, int k) {
        s = s.replace("-", "").toUpperCase();
        StringBuilder sb = new StringBuilder();
        int t = 0;
        int cnt = s.length() % k;
        if (cnt == 0) {
            cnt = k;
        }
        for (int i = 0; i < s.length(); ++i) {
            sb.append(s.charAt(i));
            ++t;
            if (t == cnt) {
                t = 0;
                cnt = k;
                if (i != s.length() - 1) {
                    sb.append('-');
                }
            }
        }
        return sb.toString();
    }
}