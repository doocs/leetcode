class Solution {
    public List<String> beforeAndAfterPuzzles(String[] phrases) {
        Map<String, Set<Integer>> sameFirstWord = new HashMap<>();
        for (int i = 0; i < phrases.length; ++i) {
            String phrase = phrases[i];
            String word = phrase.split(" ")[0];
            sameFirstWord.computeIfAbsent(word, k -> new HashSet<>()).add(i);
        }
        Set<String> res = new HashSet<>();
        for (int i = 0; i < phrases.length; ++i) {
            String phrase = phrases[i];
            String[] words = phrase.split(" ");
            String lastWord = words[words.length - 1];
            if (sameFirstWord.containsKey(lastWord)) {
                for (int j : sameFirstWord.get(lastWord)) {
                    if (i != j) {
                        List<String> t = new ArrayList<>();
                        for (int k = 0; k < words.length - 1; ++k) {
                            t.add(words[k]);
                        }
                        for (String word : phrases[j].split(" ")) {
                            t.add(word);
                        }
                        res.add(String.join(" ", t));
                    }
                }
            }
        }
        List<String> output = new ArrayList<>(res);
        Collections.sort(output);
        return output;
    }
}