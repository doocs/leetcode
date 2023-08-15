function giveGem(gem: number[], operations: number[][]): number {
    for (const [x, y] of operations) {
        const v = gem[x] >> 1;
        gem[y] += v;
        gem[x] -= v;
    }
    return Math.max(...gem) - Math.min(...gem);
}
