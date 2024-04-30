function minFlips(a: number, b: number, c: number): number {
    let ans = 0;
    for (let i = 0; i < 32; ++i) {
        const [x, y, z] = [(a >> i) & 1, (b >> i) & 1, (c >> i) & 1];
        ans += z === 0 ? x + y : x + y === 0 ? 1 : 0;
    }
    return ans;
}
