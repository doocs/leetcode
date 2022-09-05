class Solution {
    public String toGoatLatin(String sentence) {
        List<String> ans = new ArrayList<>();
        Set<Character> vowels
            = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        int i = 1;
        for (String word : sentence.split(" ")) {
            StringBuilder t = new StringBuilder();
            if (!vowels.contains(word.charAt(0))) {
                t.append(word.substring(1));
                t.append(word.charAt(0));
            } else {
                t.append(word);
            }
            t.append("ma");
            for (int j = 0; j < i; ++j) {
                t.append("a");
            }
            ++i;
            ans.add(t.toString());
        }
        return String.join(" ", ans);
    }
}