function minNumberOfSemesters(n: number, relations: number[][], k: number): number {
    const d: number[] = Array(n + 1).fill(0);
    for (const [x, y] of relations) {
        d[y] |= 1 << x;
    }
    const q: number[][] = [[0, 0]];
    const vis = new Set<number>([0]);
    while (q.length) {
        const [cur, t] = q.shift()!;
        if (cur === (1 << (n + 1)) - 2) {
            return t;
        }
        let nxt = 0;
        for (let i = 1; i <= n; ++i) {
            if ((cur & d[i]) === d[i]) {
                nxt |= 1 << i;
            }
        }
        nxt ^= cur;
        if (bitCount(nxt) <= k) {
            if (!vis.has(nxt | cur)) {
                vis.add(nxt | cur);
                q.push([nxt | cur, t + 1]);
            }
        } else {
            const x = nxt;
            while (nxt > 0) {
                if (bitCount(nxt) === k && !vis.has(nxt | cur)) {
                    vis.add(nxt | cur);
                    q.push([nxt | cur, t + 1]);
                }
                nxt = (nxt - 1) & x;
            }
        }
    }
    return 0;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
