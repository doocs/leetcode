class Solution {
    public String repeatLimitedString(String s, int repeatLimit) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 25; i >= 0; --i) {
            int j = i - 1;
            while (true) {
                for (int k = Math.min(repeatLimit, cnt[i]); k > 0; --k) {
                    cnt[i]--;
                    ans.append((char) ('a' + i));
                }
                if (cnt[i] == 0) {
                    break;
                }
                while (j >= 0 && cnt[j] == 0) {
                    --j;
                }
                if (j < 0) {
                    break;
                }
                cnt[j]--;
                ans.append((char) ('a' + j));
            }
        }
        return ans.toString();
    }
}