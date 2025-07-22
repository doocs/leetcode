function minimumCost(
    source: string,
    target: string,
    original: string[],
    changed: string[],
    cost: number[],
): number {
    const [n, m, MAX] = [source.length, original.length, Number.POSITIVE_INFINITY];
    const g: number[][] = Array.from({ length: 26 }, () => Array(26).fill(MAX));
    const getIndex = (ch: string) => ch.charCodeAt(0) - 'a'.charCodeAt(0);

    for (let i = 0; i < 26; ++i) g[i][i] = 0;
    for (let i = 0; i < m; ++i) {
        const x = getIndex(original[i]);
        const y = getIndex(changed[i]);
        const z = cost[i];
        g[x][y] = Math.min(g[x][y], z);
    }

    for (let k = 0; k < 26; ++k) {
        for (let i = 0; i < 26; ++i) {
            for (let j = 0; g[i][k] < MAX && j < 26; j++) {
                if (g[k][j] < MAX) {
                    g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
                }
            }
        }
    }

    let ans = 0;
    for (let i = 0; i < n; ++i) {
        const x = getIndex(source[i]);
        const y = getIndex(target[i]);
        if (x === y) continue;
        if (g[x][y] === MAX) return -1;
        ans += g[x][y];
    }
    return ans;
}
