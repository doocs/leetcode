function shortestPath(grid: number[][], k: number): number {
    const n = grid.length - 1;
    const m = grid[0].length - 1;
    const vis = new Set<string>();
    const dirs = [0, 1, 0, -1, 0];
    let q: Point[] = [[0, 0, k]];
    let steps = 0;

    if (k >= n + m - 1) return n + m;
    if (!n && !m) return 0;

    while (q.length) {
        const nextQ: Point[] = [];
        steps++;

        while (q.length) {
            const [y, x, k] = q.pop()!;
            const key = [y, x, k].toString();

            if (vis.has(key)) continue;
            vis.add(key);

            for (let i = 0; i < 4; i++) {
                const [dy, dx] = [dirs[i], dirs[i + 1]];
                const nextPoint: Point = [y + dy, x + dx, k];
                const value = grid[nextPoint[0]]?.[nextPoint[1]];

                if (nextPoint[0] === n && nextPoint[1] === m) return steps;

                if (value === 0) nextQ.push(nextPoint);
                else if (value === 1 && nextPoint[2]) {
                    nextPoint[2]--;
                    nextQ.push(nextPoint);
                }
            }
        }

        q = nextQ;
    }

    return -1;
}

type Point = [number, number, number];
