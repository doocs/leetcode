class Solution {
    public String convertNumber(String s) {
        String[] d
            = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        int n = s.length();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < d.length; ++j) {
                String t = d[j];
                int m = t.length();
                if (i + m <= n && s.substring(i, i + m).equals(t)) {
                    ans.append(j);
                    i += m - 1;
                    break;
                }
            }
        }
        return ans.toString();
    }
}
