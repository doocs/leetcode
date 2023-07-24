function removeOnes(grid: number[][]): boolean {
    const s = new Set<string>();
    for (const row of grid) {
        if (row[0] === 1) {
            for (let i = 0; i < row.length; i++) {
                row[i] ^= 1;
            }
        }
        const t = row.join('');
        s.add(t);
    }
    return s.size === 1;
}
