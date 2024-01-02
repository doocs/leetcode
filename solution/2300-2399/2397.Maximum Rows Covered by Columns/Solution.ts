function maximumRows(matrix: number[][], numSelect: number): number {
    const [m, n] = [matrix.length, matrix[0].length];
    const rows: number[] = Array(m).fill(0);
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (matrix[i][j]) {
                rows[i] |= 1 << j;
            }
        }
    }
    let ans = 0;
    for (let mask = 1; mask < 1 << n; ++mask) {
        if (bitCount(mask) !== numSelect) {
            continue;
        }
        let t = 0;
        for (const x of rows) {
            if ((x & mask) === x) {
                ++t;
            }
        }
        ans = Math.max(ans, t);
    }
    return ans;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
