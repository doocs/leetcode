function minMoves(matrix: string[]): number {
    const m = matrix.length,
        n = matrix[0].length;
    const g = new Map<string, [number, number][]>();
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            const c = matrix[i][j];
            if (/^[A-Za-z]$/.test(c)) {
                if (!g.has(c)) g.set(c, []);
                g.get(c)!.push([i, j]);
            }
        }
    }

    const dirs = [-1, 0, 1, 0, -1];
    const INF = Number.MAX_SAFE_INTEGER;
    const dist: number[][] = Array.from({ length: m }, () => Array(n).fill(INF));
    dist[0][0] = 0;

    const cap = m * n * 2 + 5;
    const dq = new Array<[number, number]>(cap);
    let l = cap >> 1,
        r = cap >> 1;
    const pushFront = (v: [number, number]) => {
        dq[--l] = v;
    };
    const pushBack = (v: [number, number]) => {
        dq[r++] = v;
    };
    const popFront = (): [number, number] => dq[l++];
    const empty = () => l === r;

    pushBack([0, 0]);

    while (!empty()) {
        const [i, j] = popFront();
        const d = dist[i][j];
        if (i === m - 1 && j === n - 1) return d;

        const c = matrix[i][j];
        if (g.has(c)) {
            for (const [x, y] of g.get(c)!) {
                if (d < dist[x][y]) {
                    dist[x][y] = d;
                    pushFront([x, y]);
                }
            }
            g.delete(c);
        }

        for (let idx = 0; idx < 4; idx++) {
            const x = i + dirs[idx],
                y = j + dirs[idx + 1];
            if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] !== '#' && d + 1 < dist[x][y]) {
                dist[x][y] = d + 1;
                pushBack([x, y]);
            }
        }
    }
    return -1;
}
