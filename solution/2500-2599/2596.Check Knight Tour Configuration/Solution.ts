function checkValidGrid(grid: number[][]): boolean {
    if (grid[0][0] !== 0) {
        return false;
    }
    const n = grid.length;
    const pos = Array.from(new Array(n * n), () => new Array(2).fill(0));
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            pos[grid[i][j]] = [i, j];
        }
    }
    for (let i = 1; i < n * n; ++i) {
        const p1 = pos[i - 1];
        const p2 = pos[i];
        const dx = Math.abs(p1[0] - p2[0]);
        const dy = Math.abs(p1[1] - p2[1]);
        const ok = (dx === 1 && dy === 2) || (dx === 2 && dy === 1);
        if (!ok) {
            return false;
        }
    }
    return true;
}
