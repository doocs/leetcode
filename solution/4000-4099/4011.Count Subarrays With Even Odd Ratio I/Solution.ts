function countRatioSubarrays(nums: number[], a: number, b: number): number {
    const n = nums.length;
    let ans = 0;

    for (let i = 0; i < n; i++) {
        let y = 0;

        for (let j = i; j < n; j++) {
            y += nums[j] % 2;
            const x = j - i + 1 - y;

            if (y > 0 && x * b <= y * a) {
                ans++;
            }
        }
    }

    return ans;
}
