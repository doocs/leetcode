function mostFrequentEven(nums: number[]): number {
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        if (x % 2 === 0) {
            cnt.set(x, (cnt.get(x) ?? 0) + 1);
        }
    }
    let ans = -1;
    let mx = 0;
    for (const [x, v] of cnt) {
        if (mx < v || (mx === v && ans > x)) {
            ans = x;
            mx = v;
        }
    }
    return ans;
}
