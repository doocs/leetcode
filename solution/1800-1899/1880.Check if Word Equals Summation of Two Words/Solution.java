class Solution {
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        return convert(firstWord) + convert(secondWord) == convert(targetWord);
    }

    private int convert(String word) {
        int res = 0;
        for (char c : word.toCharArray()) {
            res *= 10;
            res += (c - 'a');
        }
        return res;
    }
}