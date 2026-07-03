class Solution {
    public int numberOfSubstrings(String s) {
        int ans = 0, l = 0;
        int[] cnt = new int[3];
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            cnt[c - 'a']++;
            while (cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0) {
                cnt[s.charAt(l) - 'a']--;
                l++;
            }
            ans += l;
        }
        return ans;
    }
}