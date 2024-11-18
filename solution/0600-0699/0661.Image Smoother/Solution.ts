function imageSmoother(img: number[][]): number[][] {
    const m = img.length;
    const n = img[0].length;
    const ans: number[][] = Array.from({ length: m }, () => Array(n).fill(0));
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            let s = 0;
            let cnt = 0;
            for (let x = i - 1; x <= i + 1; ++x) {
                for (let y = j - 1; y <= j + 1; ++y) {
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        ++cnt;
                        s += img[x][y];
                    }
                }
            }
            ans[i][j] = Math.floor(s / cnt);
        }
    }
    return ans;
}
