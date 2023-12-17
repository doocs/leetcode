class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] cnt = new int[26];
        for (int i = 0; i < licensePlate.length(); ++i) {
            char c = licensePlate.charAt(i);
            if (Character.isLetter(c)) {
                cnt[Character.toLowerCase(c) - 'a']++;
            }
        }
        String ans = "";
        for (String w : words) {
            if (!ans.isEmpty() && w.length() >= ans.length()) {
                continue;
            }
            int[] t = new int[26];
            for (int i = 0; i < w.length(); ++i) {
                t[w.charAt(i) - 'a']++;
            }
            boolean ok = true;
            for (int i = 0; i < 26; ++i) {
                if (t[i] < cnt[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans = w;
            }
        }
        return ans;
    }
}