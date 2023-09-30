function largestMultipleOfThree(digits: number[]): string {
    digits.sort((a, b) => a - b);
    const n = digits.length;
    const f: number[][] = new Array(n + 1).fill(0).map(() => new Array(3).fill(-Infinity));
    f[0][0] = 0;
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j < 3; ++j) {
            f[i][j] = Math.max(f[i - 1][j], f[i - 1][(j - (digits[i - 1] % 3) + 3) % 3] + 1);
        }
    }
    if (f[n][0] <= 0) {
        return '';
    }
    const arr: number[] = [];
    for (let i = n, j = 0; i; --i) {
        const k = (j - (digits[i - 1] % 3) + 3) % 3;
        if (f[i - 1][k] + 1 === f[i][j]) {
            arr.push(digits[i - 1]);
            j = k;
        }
    }
    let i = 0;
    while (i < arr.length - 1 && arr[i] === 0) {
        ++i;
    }
    return arr.slice(i).join('');
}
