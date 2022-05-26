class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        Set<Character> letters = new HashSet<>();
        for (char c : brokenLetters.toCharArray()) {
            letters.add(c);
        }
        int res = 0;
        for (String word : text.split(" ")) {
            boolean find = false;
            for (char c : letters) {
                if (word.indexOf(c) > -1) {
                    find = true;
                    break;
                }
            }
            if (!find) {
                ++res;
            }
        }
        return res;
    }
}