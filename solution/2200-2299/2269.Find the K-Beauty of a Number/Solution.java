class Solution {
    public int divisorSubstrings(int num, int k) {
        int cnt = 0;
        String s = String.valueOf(num);
        for (int i = 0; i <= s.length() - k; i++) {
            int tmp = Integer.parseInt(s.substring(i, i + k));
            if (tmp != 0 && num % tmp == 0) {
                cnt++;
            }
        }
        return cnt;
    }
}