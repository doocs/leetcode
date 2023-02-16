function getLastMoment(n: number, left: number[], right: number[]): number {
    let ans = 0;
    for (const x of left) {
        ans = Math.max(ans, x);
    }
    for (const x of right) {
        ans = Math.max(ans, n - x);
    }
    return ans;
}
