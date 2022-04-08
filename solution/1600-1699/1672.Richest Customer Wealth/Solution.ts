function maximumWealth(accounts: number[][]): number {
    return accounts.reduce(
        (res, account) =>
            Math.max(
                res,
                account.reduce((p, v) => p + v),
            ),
        0,
    );
}
