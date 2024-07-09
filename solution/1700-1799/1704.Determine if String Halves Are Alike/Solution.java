class Solution {
    public boolean halvesAreAlike(String s) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        int n = s.length() >> 1;
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            cnt += vowels.contains(s.charAt(i)) ? 1 : 0;
            cnt -= vowels.contains(s.charAt(i + n)) ? 1 : 0;
        }
        return cnt == 0;
    }
}