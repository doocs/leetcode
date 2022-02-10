class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] counter = new int[26];
        for (char c : text.toCharArray()) {
            ++counter[c - 'a'];
        }
        counter['l' - 'a'] >>= 1;
        counter['o' - 'a'] >>= 1;
        int ans = 10000;
        for (char c : "balon".toCharArray()) {
            ans = Math.min(ans, counter[c - 'a']);
        }
        return ans;
    }
}