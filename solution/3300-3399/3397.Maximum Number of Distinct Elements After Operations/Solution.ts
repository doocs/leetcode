function maxDistinctElements(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    let [ans, pre] = [0, -Infinity];
    for (const x of nums) {
        const cur = Math.min(x + k, Math.max(x - k, pre + 1));
        if (cur > pre) {
            ++ans;
            pre = cur;
        }
    }
    return ans;
}
