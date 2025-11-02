function findMissingElements(nums: number[]): number[] {
    let [mn, mx] = [100, 0];
    const s = new Set<number>();
    for (const x of nums) {
        mn = Math.min(mn, x);
        mx = Math.max(mx, x);
        s.add(x);
    }
    const ans: number[] = [];
    for (let x = mn + 1; x < mx; ++x) {
        if (!s.has(x)) {
            ans.push(x);
        }
    }
    return ans;
}
