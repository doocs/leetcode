class MagicDictionary {
    private Set<String> s = new HashSet<>();
    private Map<String, Integer> cnt = new HashMap<>();

    /** Initialize your data structure here. */
    public MagicDictionary() {

    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            s.add(word);
            for (String p : gen(word)) {
                cnt.put(p, cnt.getOrDefault(p, 0) + 1);
            }
        }
    }

    public boolean search(String searchWord) {
        for (String p : gen(searchWord)) {
            int v = cnt.getOrDefault(p, 0);
            if (v > 1 || (v == 1 && !s.contains(searchWord))) {
                return true;
            }
        }
        return false;
    }

    private List<String> gen(String word) {
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