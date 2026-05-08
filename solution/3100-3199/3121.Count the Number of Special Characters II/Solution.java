class Solution {
    public int numberOfSpecialChars(String word) {
        int[] first = new int['z' + 1];
        int[] last = new int['z' + 1];
        for (int i = 1; i <= word.length(); ++i) {
            int j = word.charAt(i - 1);
            if (first[j] == 0) {
                first[j] = i;
            }
            last[j] = i;
        }
        int ans = 0;
        for (int i = 0; i < 26; ++i) {
            int a = 'a' + i;
            int b = 'A' + i;
            if (last[a] > 0 && last[a] < first[b]) {
                ++ans;
            }
        }
        return ans;
    }
}
