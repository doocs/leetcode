function firstCompleteIndex(arr: number[], mat: number[][]): number {
    const m = mat.length;
    const n = mat[0].length;
    const idx: Map<number, number[]> = new Map();
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            idx.set(mat[i][j], [i, j]);
        }
    }
    const row: number[] = Array(m).fill(0);
    const col: number[] = Array(n).fill(0);
    for (let k = 0; ; ++k) {
        const [i, j] = idx.get(arr[k])!;
        ++row[i];
        ++col[j];
        if (row[i] === n || col[j] === m) {
            return k;
        }
    }
}
