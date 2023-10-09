function reconstructMatrix(upper: number, lower: number, colsum: number[]): number[][] {
    const n = colsum.length;
    const ans: number[][] = Array(2)
        .fill(0)
        .map(() => Array(n).fill(0));
    for (let j = 0; j < n; ++j) {
        if (colsum[j] === 2) {
            ans[0][j] = ans[1][j] = 1;
            upper--;
            lower--;
        } else if (colsum[j] === 1) {
            if (upper > lower) {
                ans[0][j] = 1;
                upper--;
            } else {
                ans[1][j] = 1;
                lower--;
            }
        }
        if (upper < 0 || lower < 0) {
            break;
        }
    }
    return upper || lower ? [] : ans;
}
