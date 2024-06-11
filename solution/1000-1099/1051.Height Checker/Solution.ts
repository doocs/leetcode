function heightChecker(heights: number[]): number {
    const expected = [...heights].sort((a, b) => a - b);
    return heights.reduce((acc, cur, i) => acc + (cur !== expected[i] ? 1 : 0), 0);
}
