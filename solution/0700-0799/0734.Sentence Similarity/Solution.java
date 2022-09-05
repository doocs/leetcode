class Solution {
    public boolean areSentencesSimilar(
        String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) {
            return false;
        }
        Set<String> s = new HashSet<>();
        for (List<String> e : similarPairs) {
            s.add(e.get(0) + "." + e.get(1));
        }
        for (int i = 0; i < sentence1.length; ++i) {
            String a = sentence1[i], b = sentence2[i];
            if (!a.equals(b) && !s.contains(a + "." + b) && !s.contains(b + "." + a)) {
                return false;
            }
        }
        return true;
    }
}