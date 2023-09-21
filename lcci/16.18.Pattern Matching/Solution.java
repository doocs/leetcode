class Solution {
    private String pattern;
    private String value;

    public boolean patternMatching(String pattern, String value) {
        this.pattern = pattern;
        this.value = value;
        int[] cnt = new int[2];
        for (char c : pattern.toCharArray()) {
            ++cnt[c - 'a'];
        }
        int n = value.length();
        if (cnt[0] == 0) {
            return n % cnt[1] == 0 && value.substring(0, n / cnt[1]).repeat(cnt[1]).equals(value);
        }
        if (cnt[1] == 0) {
            return n % cnt[0] == 0 && value.substring(0, n / cnt[0]).repeat(cnt[0]).equals(value);
        }
        for (int la = 0; la <= n; ++la) {
            if (la * cnt[0] > n) {
                break;
            }
            if ((n - la * cnt[0]) % cnt[1] == 0) {
                int lb = (n - la * cnt[0]) / cnt[1];
                if (check(la, lb)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(int la, int lb) {
        int i = 0;
        String a = null, b = null;
        for (char c : pattern.toCharArray()) {
            if (c == 'a') {
                if (a != null && !a.equals(value.substring(i, i + la))) {
                    return false;
                }
                a = value.substring(i, i + la);
                i += la;
            } else {
                if (b != null && !b.equals(value.substring(i, i + lb))) {
                    return false;
                }
                b = value.substring(i, i + lb);
                i += lb;
            }
        }
        return !a.equals(b);
    }
}