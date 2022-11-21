function maximumWealth(accounts: number[][]): number {
    return accounts.reduce(
        (r, v) =>
            Math.max(
                r,
                v.reduce((r, v) => r + v),
            ),
        0,
    );
}
