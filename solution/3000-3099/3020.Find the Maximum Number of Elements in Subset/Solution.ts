function maximumLength(nums: number[]): number {
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) ?? 0) + 1);
    }
    let ans = cnt.has(1) ? cnt.get(1)! - ((cnt.get(1)! % 2) ^ 1) : 0;
    cnt.delete(1);
    for (let [x, _] of cnt) {
        let t = 0;
        while (cnt.has(x) && cnt.get(x)! > 1) {
            x = x * x;
            t += 2;
        }
        t += cnt.has(x) ? 1 : -1;
        ans = Math.max(ans, t);
    }
    return ans;
}
