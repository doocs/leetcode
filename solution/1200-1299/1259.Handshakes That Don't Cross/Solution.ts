function numberOfWays(numPeople: number): number {
    const mod = 10 ** 9 + 7;
    const f: number[] = Array(numPeople + 1).fill(0);
    const dfs = (i: number): number => {
        if (i < 2) {
            return 1;
        }
        if (f[i] !== 0) {
            return f[i];
        }
        for (let l = 0; l < i; l += 2) {
            const r = i - l - 2;
            f[i] += Number((BigInt(dfs(l)) * BigInt(dfs(r))) % BigInt(mod));
            f[i] %= mod;
        }
        return f[i];
    };
    return dfs(numPeople);
}
