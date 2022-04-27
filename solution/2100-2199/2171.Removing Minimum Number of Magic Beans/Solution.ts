function minimumRemoval(beans: number[]): number {
    const n = beans.length;
    let sum = beans.reduce((a, c) => a + c, 0);
    beans.sort((a, b) => a - b);
    let ans = sum;
    for (let i = 0; i < n; i++) {
        let num = beans[i];
        ans = Math.min(sum - num * (n - i), ans);
    }
    return ans;
}
