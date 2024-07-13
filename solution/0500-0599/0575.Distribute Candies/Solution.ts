function distributeCandies(candyType: number[]): number {
    const s = new Set(candyType);
    return Math.min(s.size, candyType.length >> 1);
}
