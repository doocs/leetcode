function countPoints(rings: string): number {
    const idx = (c: string) => c.charCodeAt(0) - 'A'.charCodeAt(0);
    const d: number[] = Array(26).fill(0);
    d[idx('R')] = 1;
    d[idx('G')] = 2;
    d[idx('B')] = 4;
    const mask: number[] = Array(10).fill(0);
    for (let i = 0; i < rings.length; i += 2) {
        const c = rings[i];
        const j = rings[i + 1].charCodeAt(0) - '0'.charCodeAt(0);
        mask[j] |= d[idx(c)];
    }
    return mask.filter(x => x === 7).length;
}
