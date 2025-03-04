function fairCandySwap(aliceSizes: number[], bobSizes: number[]): number[] {
    const s1 = aliceSizes.reduce((acc, cur) => acc + cur, 0);
    const s2 = bobSizes.reduce((acc, cur) => acc + cur, 0);
    const diff = (s1 - s2) >> 1;
    const s = new Set(bobSizes);
    for (const a of aliceSizes) {
        const b = a - diff;
        if (s.has(b)) {
            return [a, b];
        }
    }
    return [];
}
