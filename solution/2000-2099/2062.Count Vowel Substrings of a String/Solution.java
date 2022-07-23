class Solution {
    public int countVowelSubstrings(String word) {
        int n = word.length();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            Set<Character> t = new HashSet<>();
            for (int j = i; j < n; ++j) {
                char c = word.charAt(j);
                if (!isVowel(c)) {
                    break;
                }
                t.add(c);
                if (t.size() == 5) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}