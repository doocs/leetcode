function sumOfGoodIntegers(n: number, k: number): number {
    let ans = 0;
    const start = Math.max(1, n - k);
    const end = n + k;
    for (let x = start; x <= end; x++) {
        if ((n & x) === 0) {
            ans += x;
        }
    }
    return ans;
}
