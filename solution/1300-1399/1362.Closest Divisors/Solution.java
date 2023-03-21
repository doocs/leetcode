class Solution {
    public int[] closestDivisors(int num) {
        int[] a = f(num + 1);
        int[] b = f(num + 2);
        return Math.abs(a[0] - a[1]) < Math.abs(b[0] - b[1]) ? a : b;
    }

    private int[] f(int x) {
        for (int i = (int) Math.sqrt(x);; --i) {
            if (x % i == 0) {
                return new int[] {i, x / i};
            }
        }
    }
}