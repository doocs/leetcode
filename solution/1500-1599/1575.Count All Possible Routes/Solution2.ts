function countRoutes(locations: number[], start: number, finish: number, fuel: number): number {
    const n = locations.length;
    const f = Array.from({ length: n }, () => Array(fuel + 1).fill(0));
    for (let k = 0; k <= fuel; ++k) {
        f[finish][k] = 1;
    }
    const mod = 1e9 + 7;
    for (let k = 0; k <= fuel; ++k) {
        for (let i = 0; i < n; ++i) {
            for (let j = 0; j < n; ++j) {
                if (j !== i && Math.abs(locations[i] - locations[j]) <= k) {
                    f[i][k] = (f[i][k] + f[j][k - Math.abs(locations[i] - locations[j])]) % mod;
                }
            }
        }
    }
    return f[start][fuel];
}
