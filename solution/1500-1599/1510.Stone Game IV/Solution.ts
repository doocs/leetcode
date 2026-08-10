function winnerSquareGame(n: number): boolean {
    const f = new Array<number>(n + 1).fill(-1);

    const dfs = (i: number): boolean => {
        if (i <= 0) {
            return false;
        }
        if (f[i] !== -1) {
            return f[i] === 1;
        }

        const k = Math.floor(Math.sqrt(i));
        for (let j = 1; j <= k; j++) {
            if (!dfs(i - j * j)) {
                f[i] = 1;
                return true;
            }
        }

        f[i] = 0;
        return false;
    };

    return dfs(n);
}
