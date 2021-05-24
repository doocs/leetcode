class Solution {
    public boolean halvesAreAlike(String s) {
        int half = s.length() >> 1;
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        int s1 = 0, s2 = 0;
        for (int i = 0; i < half; ++i) {
            if (vowels.contains(s.charAt(i))) {
                ++s1;
            }
            if (vowels.contains(s.charAt(half + i))) {
                ++s2;
            }
        }
        return s1 == s2;
    }
}