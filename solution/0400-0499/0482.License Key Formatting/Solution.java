class Solution {
    public String licenseKeyFormatting(String s, int k) {
        int n = s.length();
        int cnt = (int) (n - s.chars().filter(ch -> ch == '-').count()) % k;
        if (cnt == 0) {
            cnt = k;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '-') {
                continue;
            }
            ans.append(Character.toUpperCase(c));
            --cnt;
            if (cnt == 0) {
                cnt = k;
                if (i != n - 1) {
                    ans.append('-');
                }
            }
        }
        if (ans.length() > 0 && ans.charAt(ans.length() - 1) == '-') {
            ans.deleteCharAt(ans.length() - 1);
        }
        return ans.toString();
    }
}