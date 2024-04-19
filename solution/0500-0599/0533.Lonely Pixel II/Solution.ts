function findBlackPixel(picture: string[][], target: number): number {
    const m: number = picture.length;
    const n: number = picture[0].length;
    const g: number[][] = Array.from({ length: n }, () => []);
    const rows: number[] = Array(m).fill(0);

    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (picture[i][j] === 'B') {
                ++rows[i];
                g[j].push(i);
            }
        }
    }

    let ans: number = 0;
    for (let j = 0; j < n; ++j) {
        if (g[j].length === 0 || rows[g[j][0]] !== target) {
            continue;
        }
        const i1: number = g[j][0];
        let ok: number = 0;
        if (g[j].length === rows[i1]) {
            ok = target;
            for (const i2 of g[j]) {
                if (picture[i1].join('') !== picture[i2].join('')) {
                    ok = 0;
                    break;
                }
            }
        }
        ans += ok;
    }
    return ans;
}
