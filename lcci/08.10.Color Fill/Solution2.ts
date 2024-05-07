function floodFill(image: number[][], sr: number, sc: number, newColor: number): number[][] {
    if (image[sr][sc] === newColor) {
        return image;
    }
    const q: number[][] = [[sr, sc]];
    const oc = image[sr][sc];
    image[sr][sc] = newColor;
    const dirs: number[] = [-1, 0, 1, 0, -1];
    while (q.length) {
        const [i, j] = q.pop()!;
        for (let k = 0; k < 4; ++k) {
            const [x, y] = [i + dirs[k], j + dirs[k + 1]];
            if (x >= 0 && x < image.length && y >= 0 && y < image[0].length && image[x][y] === oc) {
                q.push([x, y]);
                image[x][y] = newColor;
            }
        }
    }
    return image;
}
