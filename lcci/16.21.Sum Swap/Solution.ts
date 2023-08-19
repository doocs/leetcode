function findSwapValues(array1: number[], array2: number[]): number[] {
    const s1 = array1.reduce((a, b) => a + b, 0);
    const s2 = array2.reduce((a, b) => a + b, 0);
    let diff = s1 - s2;
    if (diff & 1) {
        return [];
    }
    diff >>= 1;
    const s: Set<number> = new Set(array2);
    for (const x of array1) {
        const y = x - diff;
        if (s.has(y)) {
            return [x, y];
        }
    }
    return [];
}
