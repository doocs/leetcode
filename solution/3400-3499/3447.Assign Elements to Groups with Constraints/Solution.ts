function assignElements(groups: number[], elements: number[]): number[] {
    const mx = Math.max(...groups);
    const d: number[] = Array(mx + 1).fill(-1);
    for (let j = 0; j < elements.length; ++j) {
        const x = elements[j];
        if (x > mx || d[x] !== -1) {
            continue;
        }
        for (let y = x; y <= mx; y += x) {
            if (d[y] === -1) {
                d[y] = j;
            }
        }
    }
    return groups.map(x => d[x]);
}
