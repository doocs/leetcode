class ValidWordAbbr {
    private Map<String, Set<String>> words;

    public ValidWordAbbr(String[] dictionary) {
        words = new HashMap<>();
        for (String word : dictionary) {
            String abbr = wordAbbr(word);
            words.computeIfAbsent(abbr, k -> new HashSet<>()).add(word);
        }
    }
    
    public boolean isUnique(String word) {
        String abbr = wordAbbr(word);
        Set<String> vals = words.get(abbr);
        return vals == null || (vals.size() == 1 && vals.contains(word));
    }

    private String wordAbbr(String word) {
        int n = word.length();
        if (n < 3) {
            return word;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(word.charAt(0)).append(n - 2).append(word.charAt(n - 1));
        return sb.toString();
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */