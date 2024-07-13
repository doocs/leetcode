function hasGroupsSizeX(deck: number[]): boolean {
    const cnt: Record<number, number> = {};
    for (const x of deck) {
        cnt[x] = (cnt[x] || 0) + 1;
    }
    const gcd = (a: number, b: number): number => (b === 0 ? a : gcd(b, a % b));
    let g = cnt[deck[0]];
    for (const [_, x] of Object.entries(cnt)) {
        g = gcd(g, x);
    }
    return g >= 2;
}
