function validSubarrays(nums: number[], k: number): number {
    const n = nums.length;
    const peaks: number[] = [];

    for (let i = 1; i < n - 1; i++) {
        if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
            peaks.push(i);
        }
    }

    let ans = 0;
    for (let j = 0; j < peaks.length; j++) {
        const p = peaks[j];

        let leftMin = Math.max(p - k, 0);
        if (j > 0) {
            leftMin = Math.max(leftMin, peaks[j - 1] + 1);
        }

        let rightMax = Math.min(p + k, n - 1);
        if (j < peaks.length - 1) {
            rightMax = Math.min(rightMax, peaks[j + 1] - 1);
        }

        ans += (p - leftMin + 1) * (rightMax - p + 1);
    }

    return ans;
}
