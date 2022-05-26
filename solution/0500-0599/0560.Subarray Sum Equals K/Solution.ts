function subarraySum(nums: number[], k: number): number {
    let ans = 0,
        s = 0;
    const counter = new Map();
    counter.set(0, 1);
    for (const num of nums) {
        s += num;
        ans += counter.get(s - k) || 0;
        counter.set(s, (counter.get(s) || 0) + 1);
    }
    return ans;
}
