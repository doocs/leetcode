export function rob(nums: number[]): number {
    const cache: Record<number, number> = {};
    const n = nums.length;
    let ans = 0;

    const dp = (i: number) => {
        if (cache[i] !== undefined) return cache[i];

        let max = 0;
        for (let j = i + 2; j < n; j++) {
            max = Math.max(max, dp(j));
        }
        cache[i] = max + nums[i];

        return cache[i];
    };

    for (let i = 0; i < n; i++) {
        ans = Math.max(ans, dp(i));
    }

    return ans;
}
