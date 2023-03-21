function construct2DArray(
    original: number[],
    m: number,
    n: number,
): number[][] {
    if (m * n != original.length) {
        return [];
    }
    const ans: number[][] = [];
    for (let i = 0; i < m * n; i += n) {
        ans.push(original.slice(i, i + n));
    }
    return ans;
}
