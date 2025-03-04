class Solution {
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        return f(firstWord) + f(secondWord) == f(targetWord);
    }

    private int f(String s) {
        int ans = 0;
        for (char c : s.toCharArray()) {
            ans = ans * 10 + (c - 'a');
        }
        return ans;
    }
}
