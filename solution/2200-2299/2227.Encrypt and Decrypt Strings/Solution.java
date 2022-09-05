class Encrypter {
    private Map<Character, String> mp = new HashMap<>();
    private Map<String, Integer> cnt = new HashMap<>();

    public Encrypter(char[] keys, String[] values, String[] dictionary) {
        for (int i = 0; i < keys.length; ++i) {
            mp.put(keys[i], values[i]);
        }
        for (String w : dictionary) {
            w = encrypt(w);
            cnt.put(w, cnt.getOrDefault(w, 0) + 1);
        }
    }

    public String encrypt(String word1) {
        StringBuilder sb = new StringBuilder();
        for (char c : word1.toCharArray()) {
            if (!mp.containsKey(c)) {
                return "";
            }
            sb.append(mp.get(c));
        }
        return sb.toString();
    }

    public int decrypt(String word2) {
        return cnt.getOrDefault(word2, 0);
    }
}

/**
 * Your Encrypter object will be instantiated and called as such:
 * Encrypter obj = new Encrypter(keys, values, dictionary);
 * String param_1 = obj.encrypt(word1);
 * int param_2 = obj.decrypt(word2);
 */