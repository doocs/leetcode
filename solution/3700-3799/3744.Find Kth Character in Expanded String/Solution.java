class Solution {
    public char kthCharacter(String s, long k) {
        for (String w : s.split(" ")) {
            long m = (1L + w.length()) * w.length() / 2;
            if (k == m) {
                return ' ';
            }
            if (k > m) {
                k -= m + 1;
            } else {
                long cur = 0;
                for (int i = 0;; ++i) {
                    cur += i + 1;
                    if (k < cur) {
                        return w.charAt(i);
                    }
                }
            }
        }
        return ' ';
    }
}
