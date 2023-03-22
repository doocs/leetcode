function subarraysDivByK(nums: number[], k: number): number {
    const counter = new Map();
    counter.set(0, 1);
    let s = 0,
        ans = 0;
    for (const num of nums) {
        s += num;
        const t = ((s % k) + k) % k;
        ans += counter.get(t) || 0;
        counter.set(t, (counter.get(t) || 0) + 1);
    }
    return ans;
}
