public class Solution {
    public int MinHeightShelves(int[][] books, int shelfWidth) {
        int n = books.Length;
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            int w = books[i - 1][0], h = books[i - 1][1];
            f[i] = f[i - 1] + h;
            for (int j = i - 1; j > 0; --j) {
                w += books[j - 1][0];
                if (w > shelfWidth) {
                    break;
                }
                h = Math.Max(h, books[j - 1][1]);
                f[i] = Math.Min(f[i], f[j - 1] + h);
            }
        }
        return f[n];
    }
}