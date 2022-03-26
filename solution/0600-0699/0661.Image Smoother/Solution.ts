function imageSmoother(img: number[][]): number[][] {
    const m = img.length;
    const n = img[0].length;
    const locations = [
        [-1, -1],
        [-1, 0],
        [-1, 1],
        [0, -1],
        [0, 0],
        [0, 1],
        [1, -1],
        [1, 0],
        [1, 1],
    ];

    const res = [];
    for (let i = 0; i < m; i++) {
        res.push([]);
        for (let j = 0; j < n; j++) {
            let sum = 0;
            let count = 0;
            for (const [y, x] of locations) {
                if ((img[i + y] || [])[j + x] != null) {
                    sum += img[i + y][j + x];
                    count++;
                }
            }
            res[i].push(Math.floor(sum / count));
        }
    }
    return res;
}
