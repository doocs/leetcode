function floodFill(
    image: number[][],
    sr: number,
    sc: number,
    newColor: number,
): number[][] {
    const m = image.length;
    const n = image[0].length;
    const target = image[sr][sc];
    const dfs = (i: number, j: number) => {
        if (
            i < 0 ||
            i === m ||
            j < 0 ||
            j === n ||
            image[i][j] !== target ||
            image[i][j] === newColor
        ) {
            return;
        }
        image[i][j] = newColor;
        dfs(i + 1, j);
        dfs(i - 1, j);
        dfs(i, j + 1);
        dfs(i, j - 1);
    };
    dfs(sr, sc);
    return image;
}
