class Solution {
    private static final Set<Character> VOWELS
        = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

    public boolean halvesAreAlike(String s) {
        int cnt = 0, n = s.length() >> 1;
        for (int i = 0; i < n; ++i) {
            cnt += VOWELS.contains(s.charAt(i)) ? 1 : 0;
            cnt -= VOWELS.contains(s.charAt(i + n)) ? 1 : 0;
        }
        return cnt == 0;
    }
}