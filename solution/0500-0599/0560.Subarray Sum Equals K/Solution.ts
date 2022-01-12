function subarraySum(nums: number[], k: number): number {
    let ans = 0,
        s = 0;
    let counter = new Map();
    counter[0] = 1;
    for (const num of nums) {
        s += num;
        ans += counter[s - k] || 0;
        counter[s] = (counter[s] || 0) + 1;
    }
    return ans;
}
