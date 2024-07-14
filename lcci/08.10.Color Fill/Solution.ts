function floodFill(image: number[][], sr: number, sc: number, newColor: number): number[][] {
    const dfs = (i: number, j: number): void => {
        if (i < 0 || i >= m) {
            return;
        }
        if (j < 0 || j >= n) {
            return;
        }
        if (image[i][j] !== oc || image[i][j] === nc) {
            return;
        }
        image[i][j] = nc;
        for (let k = 0; k < 4; ++k) {
            dfs(i + dirs[k], j + dirs[k + 1]);
        }
    };
    const dirs: number[] = [-1, 0, 1, 0, -1];
    const [m, n] = [image.length, image[0].length];
    const oc = image[sr][sc];
    const nc = newColor;
    dfs(sr, sc);
    return image;
}
