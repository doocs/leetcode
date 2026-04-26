class Solution {
    public String sortVowels(String s) {
        Set<Character> st = Set.of('a', 'e', 'i', 'o', 'u');
        List<Character> vowels = new ArrayList<>();
        Map<Character, Integer> cnt = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (!st.contains(c)) {
                continue;
            }
            if (!cnt.containsKey(c)) {
                vowels.add(c);
            }
            cnt.merge(c, 1, Integer::sum);
        }
        vowels.sort((a, b) -> cnt.get(b) - cnt.get(a));
        char[] ans = s.toCharArray();
        int i = 0;
        for (int k = 0; k < s.length(); k++) {
            char c = s.charAt(k);
            if (!st.contains(c)) {
                continue;
            }
            ans[k] = c = vowels.get(i);
            cnt.merge(c, -1, Integer::sum);
            if (cnt.get(c) == 0) {
                i++;
            }
        }
        return new String(ans);
    }
}
