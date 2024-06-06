function canIWin(maxChoosableInteger: number, desiredTotal: number): boolean {
    if (((1 + maxChoosableInteger) * maxChoosableInteger) / 2 < desiredTotal) {
        return false;
    }
    const f: Record<string, boolean> = {};
    const dfs = (mask: number, s: number): boolean => {
        if (f.hasOwnProperty(mask)) {
            return f[mask];
        }
        for (let i = 1; i <= maxChoosableInteger; ++i) {
            if (((mask >> i) & 1) ^ 1) {
                if (s + i >= desiredTotal || !dfs(mask ^ (1 << i), s + i)) {
                    return (f[mask] = true);
                }
            }
        }
        return (f[mask] = false);
    };
    return dfs(0, 0);
}
