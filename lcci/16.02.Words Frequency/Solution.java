class WordsFrequency {

    private Map<String, Integer> counter = new HashMap<>();

    public WordsFrequency(String[] book) {
        for (String word : book) {
            counter.put(word, counter.getOrDefault(word, 0) + 1);
        }
    }
    
    public int get(String word) {
        return counter.containsKey(word) ? counter.get(word) : 0;
    }
}

/**
 * Your WordsFrequency object will be instantiated and called as such:
 * WordsFrequency obj = new WordsFrequency(book);
 * int param_1 = obj.get(word);
 */