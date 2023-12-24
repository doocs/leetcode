function minimumCost(
    source: string,
    target: string,
    original: string[],
    changed: string[],
    cost: number[],
): number {
    const g: number[][] = Array.from({ length: 26 }, () => Array(26).fill(Infinity));
    for (let i = 0; i < 26; ++i) {
        g[i][i] = 0;
    }
    for (let i = 0; i < original.length; ++i) {
        let x: number = original[i].charCodeAt(0) - 'a'.charCodeAt(0);
        let y: number = changed[i].charCodeAt(0) - 'a'.charCodeAt(0);
        let z: number = cost[i];
        g[x][y] = Math.min(g[x][y], z);
    }

    for (let k = 0; k < 26; ++k) {
        for (let i = 0; i < 26; ++i) {
            for (let j = 0; j < 26; ++j) {
                g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
            }
        }
    }

    let ans: number = 0;
    let n: number = source.length;
    for (let i = 0; i < n; ++i) {
        let x: number = source.charCodeAt(i) - 'a'.charCodeAt(0);
        let y: number = target.charCodeAt(i) - 'a'.charCodeAt(0);
        if (x !== y) {
            if (g[x][y] >= Infinity) {
                return -1;
            }
            ans += g[x][y];
        }
    }
    return ans;
}
