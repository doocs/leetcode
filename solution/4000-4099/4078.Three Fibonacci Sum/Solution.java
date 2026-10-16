class Solution {
    static Set<Integer> f = new HashSet<>();

    static {
        int a = 0, b = 1;
        while (b < 1000000001) {
            int c = a + b;
            a = b;
            b = c;
            f.add(b);
        }
    }

    public boolean threeFibonacciSum(int n) {
        return n % 2 == 0 && f.contains(n / 2);
    }
}
