function findRotateSteps(ring: string, key: string): number {
    const m: number = key.length;
    const n: number = ring.length;
    const pos: number[][] = Array.from({ length: 26 }, () => []);
    for (let i = 0; i < n; ++i) {
        const j: number = ring.charCodeAt(i) - 'a'.charCodeAt(0);
        pos[j].push(i);
    }

    const f: number[][] = Array.from({ length: m }, () => Array(n).fill(1 << 30));
    for (const j of pos[key.charCodeAt(0) - 'a'.charCodeAt(0)]) {
        f[0][j] = Math.min(j, n - j) + 1;
    }

    for (let i = 1; i < m; ++i) {
        for (const j of pos[key.charCodeAt(i) - 'a'.charCodeAt(0)]) {
            for (const k of pos[key.charCodeAt(i - 1) - 'a'.charCodeAt(0)]) {
                f[i][j] = Math.min(
                    f[i][j],
                    f[i - 1][k] + Math.min(Math.abs(j - k), n - Math.abs(j - k)) + 1,
                );
            }
        }
    }

    let ans: number = 1 << 30;
    for (const j of pos[key.charCodeAt(m - 1) - 'a'.charCodeAt(0)]) {
        ans = Math.min(ans, f[m - 1][j]);
    }
    return ans;
}
