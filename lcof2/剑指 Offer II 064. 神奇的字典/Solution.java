class MagicDictionary {
    private Set<String> words;
    private Map<String, Integer> counter;

    /** Initialize your data structure here. */
    public MagicDictionary() {
        words = new HashSet<>();
        counter = new HashMap<>();
    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            words.add(word);
            for (String p : patterns(word)) {
                counter.put(p, counter.getOrDefault(p, 0) + 1);
            }
        }
    }

    public boolean search(String searchWord) {
        for (String p : patterns(searchWord)) {
            int cnt = counter.getOrDefault(p, 0);
            if (cnt > 1 || (cnt == 1 && !words.contains(searchWord))) {
                return true;
            }
        }
        return false;
    }

    private List<String> patterns(String word) {
        List<String> res = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            char c = chars[i];
            chars[i] = '*';
            res.add(new String(chars));
            chars[i] = c;
        }
        return res;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */