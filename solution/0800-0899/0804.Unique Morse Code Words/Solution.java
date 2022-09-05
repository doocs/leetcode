class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = new String[] {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....",
            "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-",
            "..-", "...-", ".--", "-..-", "-.--", "--.."};
        Set<String> s = new HashSet<>();
        for (String word : words) {
            StringBuilder t = new StringBuilder();
            for (char c : word.toCharArray()) {
                t.append(codes[c - 'a']);
            }
            s.add(t.toString());
        }
        return s.size();
    }
}