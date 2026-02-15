function champagneTower(poured: number, query_row: number, query_glass: number): number {
    const f: number[][] = Array.from({ length: 101 }, () => Array(101).fill(0));
    f[0][0] = poured;
    for (let i = 0; i <= query_row; ++i) {
        for (let j = 0; j <= i; ++j) {
            if (f[i][j] > 1) {
                const half = (f[i][j] - 1) / 2.0;
                f[i][j] = 1;
                f[i + 1][j] += half;
                f[i + 1][j + 1] += half;
            }
        }
    }
    return f[query_row][query_glass];
}
