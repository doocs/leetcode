function shortestDistance(maze: number[][], start: number[], destination: number[]): number {
    const m = maze.length;
    const n = maze[0].length;
    const dist: number[][] = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => Infinity),
    );
    const [si, sj] = start;
    const [di, dj] = destination;
    dist[si][sj] = 0;
    const q: number[][] = [[si, sj]];
    const dirs = [-1, 0, 1, 0, -1];
    while (q.length) {
        const [i, j] = q.shift()!;
        for (let d = 0; d < 4; ++d) {
            let [x, y, k] = [i, j, dist[i][j]];
            const [a, b] = [dirs[d], dirs[d + 1]];
            while (x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && maze[x + a][y + b] === 0) {
                x += a;
                y += b;
                ++k;
            }
            if (k < dist[x][y]) {
                dist[x][y] = k;
                q.push([x, y]);
            }
        }
    }
    return dist[di][dj] === Infinity ? -1 : dist[di][dj];
}
