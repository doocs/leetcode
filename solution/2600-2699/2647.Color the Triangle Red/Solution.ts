function colorRed(n: number): number[][] {
    const ans: number[][] = [[1, 1]];
    for (let i = n, k = 0; i > 1; --i, k = (k + 1) % 4) {
        if (k === 0) {
            for (let j = 1; j < i << 1; j += 2) {
                ans.push([i, j]);
            }
        } else if (k === 1) {
            ans.push([i, 2]);
        } else if (k === 2) {
            for (let j = 3; j < i << 1; j += 2) {
                ans.push([i, j]);
            }
        } else {
            ans.push([i, 1]);
        }
    }
    return ans;
}
