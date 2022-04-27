class Solution {
    public int maximumSwap(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int n = chars.length;
        for (int i = 0; i < n - 1; ++i) {
            int mx = i + 1;
            for (int j = i + 1; j < n; ++j) {
                if (chars[j] >= chars[mx]) {
                    mx = j;
                }
            }
            if (chars[i] < chars[mx]) {
                char t = chars[i];
                chars[i] = chars[mx];
                chars[mx] = t;
                break;
            }
        }
        return Integer.parseInt(String.valueOf(chars));
    }
}