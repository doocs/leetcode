function nearestExit(maze: string[][], entrance: number[]): number {
    const m = maze.length;
    const n = maze[0].length;
    const vis = Array.from({ length: m }, () => Array.from({ length: n }, () => false));
    const dir = [0, 1, 0, -1, 0];
    const q = [[...entrance, 0]];

    for (const [i, j, ans] of q) {
        if (vis[i][j]) continue;
        vis[i][j] = true;

        for (let d = 0; d < 4; d++) {
            const [y, x] = [i + dir[d], j + dir[d + 1]];
            const v = maze[y]?.[x];

            if (!v && ans) return ans;
            if (v === '.') q.push([y, x, ans + 1]);
        }
    }

    return -1;
}
