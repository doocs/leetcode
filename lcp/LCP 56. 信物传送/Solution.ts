function conveyorBelt(matrix: string[], start: number[], end: number[]): number {
    const dirs = [-1, 0, 1, 0, -1];
    const d: Map<string, number> = new Map();
    d.set('^', 0);
    d.set('>', 1);
    d.set('v', 2);
    d.set('<', 3);
    const q: number[][] = [start];
    const m = matrix.length;
    const n = matrix[0].length;
    const dist: number[][] = Array(m)
        .fill(0)
        .map(() => Array(n).fill(1 << 30));
    dist[start[0]][start[1]] = 0;
    while (true) {
        const [i, j] = q.shift()!;
        if (i === end[0] && j === end[1]) {
            return dist[i][j];
        }
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            const t = dist[i][j] + (d.get(matrix[i][j]) === k ? 0 : 1);
            if (x >= 0 && x < m && y >= 0 && y < n && t < dist[x][y]) {
                dist[x][y] = t;
                if (t == dist[i][j]) {
                    q.unshift([x, y]);
                } else {
                    q.push([x, y]);
                }
            }
        }
    }
}
