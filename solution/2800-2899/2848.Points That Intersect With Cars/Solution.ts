function numberOfPoints(nums: number[][]): number {
    const d: number[] = Array(102).fill(0);
    for (const [start, end] of nums) {
        ++d[start];
        --d[end + 1];
    }
    let ans = 0;
    let s = 0;
    for (const x of d) {
        s += x;
        ans += s > 0 ? 1 : 0;
    }
    return ans;
}
