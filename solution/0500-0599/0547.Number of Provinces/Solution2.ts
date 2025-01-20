function findCircleNum(isConnected: number[][]): number {
    const n = isConnected.length;
    const p: number[] = Array.from({ length: n }, (_, i) => i);
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    let ans = n;
    for (let i = 0; i < n; ++i) {
        for (let j = i + 1; j < n; ++j) {
            if (isConnected[i][j]) {
                const pa = find(i);
                const pb = find(j);
                if (pa !== pb) {
                    p[pa] = pb;
                    --ans;
                }
            }
        }
    }
    return ans;
}
