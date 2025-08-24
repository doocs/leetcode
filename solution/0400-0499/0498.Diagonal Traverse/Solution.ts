function findDiagonalOrder(mat: number[][]): number[] {
    const m = mat.length;
    const n = mat[0].length;
    const ans: number[] = [];
    for (let k = 0; k < m + n - 1; k++) {
        const t: number[] = [];
        let i = k < n ? 0 : k - n + 1;
        let j = k < n ? k : n - 1;
        while (i < m && j >= 0) {
            t.push(mat[i][j]);
            i++;
            j--;
        }
        if (k % 2 === 0) {
            t.reverse();
        }
        ans.push(...t);
    }
    return ans;
}
