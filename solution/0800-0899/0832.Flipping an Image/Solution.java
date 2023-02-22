class Solution {
    public int[][] flipAndInvertImage(int[][] image) {
        for (var row : image) {
            int i = 0, j = row.length - 1;
            for (; i < j; ++i, --j) {
                if (row[i] == row[j]) {
                    row[i] ^= 1;
                    row[j] ^= 1;
                }
            }
            if (i == j) {
                row[i] ^= 1;
            }
        }
        return image;
    }
}