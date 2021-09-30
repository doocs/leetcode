class WordDistance {
    private Map<String, List<Integer>> words;

    public WordDistance(String[] wordsDict) {
        words = new HashMap<>();
        for (int i = 0; i < wordsDict.length; ++i) {
            words.computeIfAbsent(wordsDict[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> idx1 = words.get(word1);
        List<Integer> idx2 = words.get(word2);
        int i1 = 0, i2 = 0, shortest = Integer.MAX_VALUE;
        while (i1 < idx1.size() && i2 < idx2.size()) {
            shortest = Math.min(shortest, Math.abs(idx1.get(i1) - idx2.get(i2)));
            boolean smaller = idx1.get(i1) < idx2.get(i2);
            if (smaller) {
                ++i1;
            } else {
                ++i2;
            }
        }
        return shortest;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(wordsDict);
 * int param_1 = obj.shortest(word1,word2);
 */