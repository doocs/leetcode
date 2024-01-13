function generateMatrix(n: number): number[][] {
    const res = new Array(n).fill(0).map(() => new Array(n).fill(0));
    let num = 1;
    for (let i = 0; i < Math.floor(n / 2); i++) {
        for (let j = i; j < n - i - 1; j++) {
            res[i][j] = num++;
        }
        for (let j = i; j < n - i - 1; j++) {
            res[j][n - i - 1] = num++;
        }
        for (let j = i; j < n - i - 1; j++) {
            res[n - i - 1][n - j - 1] = num++;
        }
        for (let j = i; j < n - i - 1; j++) {
            res[n - j - 1][i] = num++;
        }
    }
    if (n % 2 === 1) {
        res[n >> 1][n >> 1] = num;
    }
    return res;
}
