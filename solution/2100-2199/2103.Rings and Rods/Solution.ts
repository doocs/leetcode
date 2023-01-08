function countPoints(rings: string): number {
    const helper = (c: string) => c.charCodeAt(0) - 'A'.charCodeAt(0);
    const n = rings.length;
    const target = (1 << helper('R')) + (1 << helper('G')) + (1 << helper('B'));
    const count = new Array(10).fill(0);
    for (let i = 0; i < n; i += 2) {
        count[rings[i + 1]] |= 1 << helper(rings[i]);
    }
    return count.reduce((r, v) => (r += v === target ? 1 : 0), 0);
}
