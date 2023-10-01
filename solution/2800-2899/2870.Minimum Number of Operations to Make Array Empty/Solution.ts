function minOperations(nums: number[]): number {
    const count: Map<number, number> = new Map();
    for (const num of nums) {
        count.set(num, (count.get(num) ?? 0) + 1);
    }
    let ans = 0;
    for (const [_, c] of count) {
        if (c < 2) {
            return -1;
        }
        ans += ((c + 2) / 3) | 0;
    }
    return ans;
}
