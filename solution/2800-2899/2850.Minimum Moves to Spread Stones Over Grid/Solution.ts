function minimumMoves(grid: number[][]): number {
    const q: string[] = [f(grid)];
    const vis: Set<string> = new Set([f(grid)]);
    const dirs: number[] = [-1, 0, 1, 0, -1];

    for (let ans = 0; ; ans++) {
        let sz = q.length;
        while (sz-- > 0) {
            const p = q.shift()!;
            if (p === '111111111') {
                return ans;
            }
            const cur = g(p);

            for (let i = 0; i < 3; i++) {
                for (let j = 0; j < 3; j++) {
                    if (cur[i][j] > 1) {
                        for (let d = 0; d < 4; d++) {
                            const x = i + dirs[d],
                                y = j + dirs[d + 1];
                            if (x >= 0 && x < 3 && y >= 0 && y < 3 && cur[x][y] < 2) {
                                const nxt = cur.map(row => [...row]);
                                nxt[i][j]--;
                                nxt[x][y]++;
                                const s = f(nxt);
                                if (!vis.has(s)) {
                                    vis.add(s);
                                    q.push(s);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

function f(grid: number[][]): string {
    return grid.flat().join('');
}

function g(s: string): number[][] {
    return Array.from({ length: 3 }, (_, i) =>
        Array.from({ length: 3 }, (_, j) => Number(s[i * 3 + j])),
    );
}
