class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] counter = count(licensePlate.toLowerCase());
        String ans = null;
        int n = 16;
        for (String word : words) {
            if (n <= word.length()) {
                continue;
            }
            int[] t = count(word);
            if (check(counter, t)) {
                n = word.length();
                ans = word;
            }
        }
        return ans;
    }

    private int[] count(String word) {
        int[] counter = new int[26];
        for (char c : word.toCharArray()) {
            if (Character.isLetter(c)) {
                ++counter[c - 'a'];
            }
        }
        return counter;
    }

    private boolean check(int[] counter1, int[] counter2) {
        for (int i = 0; i < 26; ++i) {
            if (counter1[i] > counter2[i]) {
                return false;
            }
        }
        return true;
    }
}