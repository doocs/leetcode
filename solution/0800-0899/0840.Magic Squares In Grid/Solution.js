function numMagicSquaresInside(grid) {
    const m = grid.length;
    const n = grid[0].length;
    const check = (i, j) => {
        if (i + 3 > m || j + 3 > n) {
            return 0;
        }
        const cnt = Array(16).fill(0);
        const row = Array(3).fill(0);
        const col = Array(3).fill(0);
        let [a, b] = [0, 0];
        for (let x = i; x < i + 3; ++x) {
            for (let y = j; y < j + 3; ++y) {
                const v = grid[x][y];
                if (v < 1 || v > 9 || ++cnt[v] > 1) {
                    return 0;
                }
                row[x - i] += v;
                col[y - j] += v;
                if (x - i === y - j) {
                    a += v;
                }
                if (x - i === 2 - (y - j)) {
                    b += v;
                }
            }
        }
        if (a !== b) {
            return 0;
        }
        for (let k = 0; k < 3; ++k) {
            if (row[k] !== a || col[k] !== a) {
                return 0;
            }
        }
        return 1;
    };
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            ans += check(i, j);
        }
    }
    return ans;
}
