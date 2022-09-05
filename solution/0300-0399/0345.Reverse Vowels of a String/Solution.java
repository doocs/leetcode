class Solution {
    public String reverseVowels(String s) {
        Set<Character> vowels
            = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        int i = 0, j = s.length() - 1;
        char[] chars = s.toCharArray();
        while (i < j) {
            if (!vowels.contains(chars[i])) {
                ++i;
                continue;
            }
            if (!vowels.contains(chars[j])) {
                --j;
                continue;
            }
            char t = chars[i];
            chars[i] = chars[j];
            chars[j] = t;
            ++i;
            --j;
        }
        return new String(chars);
    }
}