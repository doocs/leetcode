function numOfWays(n: number): number {
    const mod: number = 10 ** 9 + 7;
    let f0: number = 6;
    let f1: number = 6;

    for (let i = 1; i < n; i++) {
        const g0: number = (3 * f0 + 2 * f1) % mod;
        const g1: number = (2 * f0 + 2 * f1) % mod;
        f0 = g0;
        f1 = g1;
    }

    return (f0 + f1) % mod;
}
