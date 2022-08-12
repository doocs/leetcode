class CombinationIterator {
    private int curr;
    private int size;
    private char[] cs;

    public CombinationIterator(String characters, int combinationLength) {
        int n = characters.length();
        curr = (1 << n) - 1;
        size = combinationLength;
        cs = new char[n];
        for (int i = 0; i < n; ++i) {
            cs[i] = characters.charAt(n - i - 1);
        }
    }
    
    public String next() {
        while (curr >= 0 && Integer.bitCount(curr) != size) {
            --curr;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < cs.length; ++i) {
            if (((curr >> i) & 1) == 1) {
                ans.append(cs[i]);
            }
        }
        --curr;
        return ans.reverse().toString();
    }
    
    public boolean hasNext() {
        while (curr >= 0 && Integer.bitCount(curr) != size) {
            --curr;
        }
        return curr >= 0;
    }
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */