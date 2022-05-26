class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        dfs(image, sr, sc, oldColor, newColor);
        return image;
    }

    private void dfs(int[][] image, int sr, int sc, int oldColor, int newColor) {
        if (sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length) {
            return;
        }

        int color = image[sr][sc];
        if (color != newColor && color == oldColor) {
            image[sr][sc] = newColor;
            // up down left right
            dfs(image, sr, sc + 1, oldColor, newColor);
            dfs(image, sr, sc - 1, oldColor, newColor);
            dfs(image, sr + 1, sc, oldColor, newColor);
            dfs(image, sr - 1, sc, oldColor, newColor);
        }
    }
}