function maxNumberOfFamilies(n: number, reservedSeats: number[][]): number {
    const d: Map<number, number> = new Map();
    for (const [i, j] of reservedSeats) {
        d.set(i, (d.get(i) ?? 0) | (1 << (10 - j)));
    }
    let ans = (n - d.size) << 1;
    const masks = [0b0111100000, 0b0000011110, 0b0001111000];
    for (let [_, x] of d) {
        for (const mask of masks) {
            if ((x & mask) === 0) {
                x |= mask;
                ++ans;
            }
        }
    }
    return ans;
}
