function minDistance(
    height: number,
    width: number,
    tree: number[],
    squirrel: number[],
    nuts: number[][],
): number {
    const [tr, tc] = tree;
    const [sr, sc] = squirrel;
    const s = nuts.reduce((acc, [r, c]) => acc + (Math.abs(tr - r) + Math.abs(tc - c)) * 2, 0);
    let ans = Infinity;
    for (const [r, c] of nuts) {
        const a = Math.abs(tr - r) + Math.abs(tc - c);
        const b = Math.abs(sr - r) + Math.abs(sc - c);
        ans = Math.min(ans, s - a + b);
    }
    return ans;
}
