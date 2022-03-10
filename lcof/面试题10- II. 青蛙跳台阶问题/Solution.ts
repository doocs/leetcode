function numWays(n: number): number {
    let a = 0;
    let b = 1;
    for (let i = 0; i < n; i++) {
        [a, b] = [b, (a + b) % 1000000007];
    }
    return b;
}
