class CombinationIterator {
    private int n;
    private int combinationLength;
    private String characters;
    private StringBuilder t = new StringBuilder();
    private List<String> cs = new ArrayList<>();
    private int idx = 0;

    public CombinationIterator(String characters, int combinationLength) {
        n = characters.length();
        this.combinationLength = combinationLength;
        this.characters = characters;
        dfs(0);
    }

    public String next() {
        return cs.get(idx++);
    }

    public boolean hasNext() {
        return idx < cs.size();
    }

    private void dfs(int i) {
        if (t.length() == combinationLength) {
            cs.add(t.toString());
            return;
        }
        if (i == n) {
            return;
        }
        t.append(characters.charAt(i));
        dfs(i + 1);
        t.deleteCharAt(t.length() - 1);
        dfs(i + 1);
    }
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */