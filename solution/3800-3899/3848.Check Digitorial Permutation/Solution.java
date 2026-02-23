class Solution {
    private static final int[] f = new int[10];

    static {
        f[0] = 1;
        for (int i = 1; i < 10; i++) {
            f[i] = f[i - 1] * i;
        }
    }

    public boolean isDigitorialPermutation(int n) {
        int x = 0;
        int y = n;

        while (y > 0) {
            x += f[y % 10];
            y /= 10;
        }

        char[] a = String.valueOf(x).toCharArray();
        char[] b = String.valueOf(n).toCharArray();

        Arrays.sort(a);
        Arrays.sort(b);

        return Arrays.equals(a, b);
    }
}
