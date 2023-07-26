function nimGame(piles: number[]): boolean {
    const p: number[] = Array(8).fill(1);
    for (let i = 1; i < 8; ++i) {
        p[i] = p[i - 1] * 8;
    }
    const f = (piles: number[]): number => {
        let st = 0;
        for (let i = 0; i < piles.length; ++i) {
            st += piles[i] * p[i];
        }
        return st;
    };
    const memo: Map<number, boolean> = new Map();
    const dfs = (piles: number[]): boolean => {
        const st = f(piles);
        if (memo.has(st)) {
            return memo.get(st)!;
        }
        for (let i = 0; i < piles.length; ++i) {
            for (let j = 1; j <= piles[i]; ++j) {
                piles[i] -= j;
                if (!dfs(piles)) {
                    piles[i] += j;
                    memo.set(st, true);
                    return true;
                }
                piles[i] += j;
            }
        }
        memo.set(st, false);
        return false;
    };
    return dfs(piles);
}
