function champagneTower(poured: number, query_row: number, query_glass: number): number {
    let f: number[] = [poured];
    for (let i = 1; i <= query_row; ++i) {
        const g: number[] = new Array(i + 1).fill(0);
        for (let j = 0; j < i; ++j) {
            if (f[j] > 1) {
                const half = (f[j] - 1) / 2.0;
                g[j] += half;
                g[j + 1] += half;
            }
        }
        f = g;
    }
    return Math.min(1, f[query_glass]);
}
