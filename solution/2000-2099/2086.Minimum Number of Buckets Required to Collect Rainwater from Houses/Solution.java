class Solution {
    public int minimumBuckets(String street) {
        int last = -2, n = street.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            char c = street.charAt(i);
            if (c == 'H') {
                if (last == i - 1) {
                    continue;
                }
                if (i + 1 < n && street.charAt(i + 1) == '.') {
                    last = i + 1;
                } else if (i - 1 >= 0 && street.charAt(i - 1) == '.') {
                    last = i - 1;
                } else {
                    return -1;
                }
                ans++;
            }
        }
        return ans;
    }
}
