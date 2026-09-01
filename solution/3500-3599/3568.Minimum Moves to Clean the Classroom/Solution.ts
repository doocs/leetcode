function minMoves(classroom: string[], energy: number): number {
    const m = classroom.length;
    const n = classroom[0].length;
    const d: number[][] = Array.from({ length: m }, () => Array(n).fill(0));
    let x = 0;
    let y = 0;
    let cnt = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            const c = classroom[i][j];
            if (c === 'S') {
                x = i;
                y = j;
            } else if (c === 'L') {
                d[i][j] = cnt++;
            }
        }
    }
    if (cnt === 0) {
        return 0;
    }
    const vis = Array.from({ length: m }, () =>
        Array.from({ length: n }, () =>
            Array.from({ length: energy + 1 }, () => new Uint8Array(1 << cnt)),
        ),
    );
    let q: number[][] = [[x, y, energy, (1 << cnt) - 1]];
    vis[x][y][energy][(1 << cnt) - 1] = 1;
    const dirs = [-1, 0, 1, 0, -1];
    let ans = 0;
    while (q.length) {
        const t = q;
        q = [];
        for (const [i, j, curEnergy, mask] of t) {
            if (mask === 0) {
                return ans;
            }
            if (curEnergy <= 0) {
                continue;
            }
            for (let k = 0; k < 4; ++k) {
                const nx = i + dirs[k];
                const ny = j + dirs[k + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && classroom[nx][ny] !== 'X') {
                    const nxtEnergy = classroom[nx][ny] === 'R' ? energy : curEnergy - 1;
                    let nxtMask = mask;
                    if (classroom[nx][ny] === 'L') {
                        nxtMask &= ~(1 << d[nx][ny]);
                    }
                    if (!vis[nx][ny][nxtEnergy][nxtMask]) {
                        vis[nx][ny][nxtEnergy][nxtMask] = 1;
                        q.push([nx, ny, nxtEnergy, nxtMask]);
                    }
                }
            }
        }
        ++ans;
    }
    return -1;
}
