function minimumCost(n: number, connections: number[][]): number {
    const p: number[] = Array.from({ length: n }, (_, i) => i);
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    connections.sort((a, b) => a[2] - b[2]);
    let ans = 0;
    for (const [x, y, cost] of connections) {
        if (find(x - 1) === find(y - 1)) {
            continue;
        }
        p[find(x - 1)] = find(y - 1);
        ans += cost;
        if (--n === 1) {
            return ans;
        }
    }
    return -1;
}
