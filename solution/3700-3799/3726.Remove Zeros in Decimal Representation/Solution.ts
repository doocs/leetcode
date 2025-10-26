function removeZeros(n: number): number {
    let k = 1;
    let ans = 0;
    while (n) {
        const x = n % 10;
        if (x) {
            ans = k * x + ans;
            k *= 10;
        }
        n = Math.floor(n / 10);
    }
    return ans;
}
