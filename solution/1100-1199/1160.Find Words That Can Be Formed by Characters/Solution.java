class Solution {
    public int countCharacters(String[] words, String chars) {
        int[] counter = count(chars);
        int ans = 0;
        for (String word : words) {
            int[] cnt = count(word);
            if (check(counter, cnt)) {
                ans += word.length();
            }
        }
        return ans;
    }

    private int[] count(String s) {
        int[] counter = new int[26];
        for (char c : s.toCharArray()) {
            ++counter[c - 'a'];
        }
        return counter;
    }

    private boolean check(int[] cnt1, int[] cnt2) {
        for (int i = 0; i < 26; ++i) {
            if (cnt1[i] < cnt2[i]) {
                return false;
            }
        }
        return true;
    }
}