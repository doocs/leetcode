function kthLargestValue(matrix: number[][], k: number): number {
    const m: number = matrix.length;
    const n: number = matrix[0].length;
    const s = Array.from({ length: m + 1 }, () => Array.from({ length: n + 1 }, () => 0));
    const ans: number[] = [];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            s[i + 1][j + 1] = s[i + 1][j] ^ s[i][j + 1] ^ s[i][j] ^ matrix[i][j];
            ans.push(s[i + 1][j + 1]);
        }
    }
    ans.sort((a, b) => b - a);
    return ans[k - 1];
}
