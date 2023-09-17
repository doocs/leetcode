function numberOfPoints(nums: number[][]): number {
    const d: number[] = Array(110).fill(0);
    for (const [a, b] of nums) {
        d[a]++;
        d[b + 1]--;
    }
    let ans = 0;
    let s = 0;
    for (const x of d) {
        s += x;
        if (s > 0) {
            ans++;
        }
    }
    return ans;
}
