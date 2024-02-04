class Solution {
    public int minimumTimeToInitialState(String word, int k) {
        int n = word.length();
        for (int i = 1; i <= 10000; i++) {
            int re = i * k;
            if (re >= n) {
                return i;
            }
            String str = word.substring(re);
            boolean flag = true;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) != word.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return 0;
    }
}
