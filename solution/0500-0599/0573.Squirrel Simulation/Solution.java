class Solution {
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int ans = Integer.MAX_VALUE;
        int s = 0;
        for (int[] a : nuts) {
            s += f(a, tree);
        }
        s *= 2;
        for (int[] a : nuts) {
            int c = f(a, tree);
            int d = f(a, squirrel) + c;
            ans = Math.min(ans, s + d - c * 2);
        }
        return ans;
    }

    private int f(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}