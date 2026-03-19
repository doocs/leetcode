function canMouseWin(grid: string[], catJump: number, mouseJump: number): boolean {
    const m = grid.length;
    const n = grid[0].length;

    let catStart = 0;
    let mouseStart = 0;
    let food = 0;

    const dirs = [-1, 0, 1, 0, -1];

    const gMouse: number[][] = Array.from({ length: m * n }, () => []);
    const gCat: number[][] = Array.from({ length: m * n }, () => []);

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            const c = grid[i][j];

            if (c === '#') continue;

            const v = i * n + j;

            if (c === 'C') catStart = v;
            else if (c === 'M') mouseStart = v;
            else if (c === 'F') food = v;

            for (let d = 0; d < 4; d++) {
                const a = dirs[d];
                const b = dirs[d + 1];

                for (let k = 0; k <= mouseJump; k++) {
                    const x = i + k * a;
                    const y = j + k * b;

                    if (!(0 <= x && x < m && 0 <= y && y < n && grid[x][y] !== '#')) break;

                    gMouse[v].push(x * n + y);
                }

                for (let k = 0; k <= catJump; k++) {
                    const x = i + k * a;
                    const y = j + k * b;

                    if (!(0 <= x && x < m && 0 <= y && y < n && grid[x][y] !== '#')) break;

                    gCat[v].push(x * n + y);
                }
            }
        }
    }

    function getPrevStates(m: number, c: number, t: number, ans: number[][][]): number[][] {
        const pt = t ^ 1;
        const pre: number[][] = [];

        if (pt === 1) {
            for (const pc of gCat[c]) {
                if (ans[m][pc][1] === 0) pre.push([m, pc, pt]);
            }
        } else {
            for (const pm of gMouse[m]) {
                if (ans[pm][c][0] === 0) pre.push([pm, c, pt]);
            }
        }

        return pre;
    }

    function calc(): number {
        const N = m * n;

        const degree: number[][][] = Array.from({ length: N }, () =>
            Array.from({ length: N }, () => [0, 0]),
        );

        const ans: number[][][] = Array.from({ length: N }, () =>
            Array.from({ length: N }, () => [0, 0]),
        );

        for (let i = 0; i < N; i++) {
            for (let j = 0; j < N; j++) {
                degree[i][j][0] = gMouse[i].length;
                degree[i][j][1] = gCat[j].length;
            }
        }

        const q: number[][] = [];

        for (let i = 0; i < N; i++) {
            ans[food][i][1] = 1;
            ans[i][food][0] = 2;
            ans[i][i][1] = 2;
            ans[i][i][0] = 2;

            q.push([food, i, 1]);
            q.push([i, food, 0]);
            q.push([i, i, 0]);
            q.push([i, i, 1]);
        }

        while (q.length) {
            const [mPos, cPos, t] = q.shift()!;
            const currentAns = ans[mPos][cPos][t];

            for (const [pm, pc, pt] of getPrevStates(mPos, cPos, t, ans)) {
                if (pt === currentAns - 1) {
                    ans[pm][pc][pt] = currentAns;
                    q.push([pm, pc, pt]);
                } else {
                    degree[pm][pc][pt]--;
                    if (degree[pm][pc][pt] === 0) {
                        ans[pm][pc][pt] = currentAns;
                        q.push([pm, pc, pt]);
                    }
                }
            }
        }

        return ans[mouseStart][catStart][0];
    }

    return calc() === 1;
}
