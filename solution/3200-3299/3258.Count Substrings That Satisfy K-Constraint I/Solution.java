class Solution {
    public int countKConstraintSubstrings(String s, int k) {
        int[] cnt = new int[2];
        int ans = 0, l = 0;
        for (int r = 0; r < s.length(); ++r) {
            ++cnt[s.charAt(r) - '0'];
            while (cnt[0] > k && cnt[1] > k) {
                cnt[s.charAt(l++) - '0']--;
            }
            ans += r - l + 1;
        }
        return ans;
    }
}
