function countUnguarded(m, n, guards, walls) {
    let c = 0;
    const mtx = Array.from({ length: m }, () => Array(n).fill(0));
    for (const [i, j] of guards) mtx[i][j] = 2;
    for (const [i, j] of walls) mtx[i][j] = 2;

    const dfs = (i, j, dx, dy) => {
        [i, j] = [i + dx, j + dy];

        if (i < 0 || m <= i || j < 0 || n <= j || mtx[i][j] === 2) return;

        if (mtx[i][j] === 0) {
            mtx[i][j] = 1;
            c++;
        }

        dfs(i, j, dx, dy);
    };

    const DIRS = [-1, 0, 1, 0, -1];
    for (const [i, j] of guards) {
        for (let k = 0; k < 4; k++) {
            const [dx, dy] = [DIRS[k], DIRS[k + 1]];
            dfs(i, j, dx, dy);
        }
    }

    return m * n - guards.length - walls.length - c;
}
