function winnerSquareGame(n: number): boolean {
    const f: boolean[] = new Array(n + 1).fill(false);
    for (let i = 1; i <= n; ++i) {
        for (let j = 1; j * j <= i; ++j) {
            if (!f[i - j * j]) {
                f[i] = true;
                break;
            }
        }
    }
    return f[n];
}
