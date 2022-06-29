function countHousePlacements(n: number): number {
    const mod = BigInt(10 ** 9 + 7);
    let pre = 1n,
        count = 2n;
    for (let i = 2; i <= n; i++) {
        [count, pre] = [(count + pre) % mod, count];
    }
    return Number(count ** 2n % mod);
}
