class Solution {
    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) {
            return false;
        }
        Set<String> pairs = new HashSet<>();
        for (List<String> pair : similarPairs) {
            pairs.add(pair.get(0) + "." + pair.get(1));
        }
        for (int i = 0; i < sentence1.length; ++i) {
            boolean similar =  pairs.contains(sentence1[i] + "." + sentence2[i]) || pairs.contains(sentence2[i] + "." + sentence1[i]) || sentence1[i].equals(sentence2[i]);
            if (!similar) {
                return false;
            }
        }
        return true;
    }
}