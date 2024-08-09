function numMagicSquaresInside(grid) {
    const [m, n] = [grid.length, grid[0].length];
    if (m < 3 || n < 3) return 0;

    const check = (y, x) => {
        const g = grid;
        if (g[y + 1][x + 1] !== 5) return false;

        const cells = [
            g[y][x],
            g[y][x + 1],
            g[y][x + 2],
            g[y + 1][x + 2],
            g[y + 2][x + 2],
            g[y + 2][x + 1],
            g[y + 2][x],
            g[y + 1][x],
        ];

        const i = cells.indexOf(2);
        if (i === -1) return false;
        cells.push(...cells.splice(0, i));

        const circle = [2, 9, 4, 3, 8, 1, 6, 7];
        const reverseCircle = [2, 7, 6, 1, 8, 3, 4, 9];

        if (cells.every((x, i) => x === circle[i])) return true;
        if (cells.every((x, i) => x === reverseCircle[i])) return true;

        return false;
    };

    let res = 0;
    for (let i = 0; i < m - 2; i++) {
        for (let j = 0; j < n - 2; j++) {
            res += +check(i, j);
        }
    }

    return res;
}
