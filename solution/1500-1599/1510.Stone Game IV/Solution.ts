function winnerSquareGame(n: number): boolean {
    const f: number[] = new Array(n + 1).fill(0);
    const dfs = (i: number): boolean => {
        if (i <= 0) {
            return false;
        }
        if (f[i] !== 0) {
            return f[i] === 1;
        }
        for (let j = 1; j * j <= i; ++j) {
            if (!dfs(i - j * j)) {
                f[i] = 1;
                return true;
            }
        }
        f[i] = -1;
        return false;
    };
    return dfs(n);
}
