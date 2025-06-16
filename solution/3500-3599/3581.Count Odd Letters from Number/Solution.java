class Solution {
    private static final Map<Integer, String> d = new HashMap<>();
    static {
        d.put(0, "zero");
        d.put(1, "one");
        d.put(2, "two");
        d.put(3, "three");
        d.put(4, "four");
        d.put(5, "five");
        d.put(6, "six");
        d.put(7, "seven");
        d.put(8, "eight");
        d.put(9, "nine");
    }

    public int countOddLetters(int n) {
        int mask = 0;
        while (n > 0) {
            int x = n % 10;
            n /= 10;
            for (char c : d.get(x).toCharArray()) {
                mask ^= 1 << (c - 'a');
            }
        }
        return Integer.bitCount(mask);
    }
}
