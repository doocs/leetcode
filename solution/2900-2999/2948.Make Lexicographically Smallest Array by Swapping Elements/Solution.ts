function lexicographicallySmallestArray(nums: number[], limit: number): number[] {
    const n: number = nums.length;
    const idx: number[] = Array.from({ length: n }, (_, i) => i);
    idx.sort((i, j) => nums[i] - nums[j]);
    const ans: number[] = Array(n).fill(0);
    for (let i = 0; i < n; ) {
        let j = i + 1;
        while (j < n && nums[idx[j]] - nums[idx[j - 1]] <= limit) {
            j++;
        }
        const t: number[] = idx.slice(i, j).sort((a, b) => a - b);
        for (let k: number = i; k < j; k++) {
            ans[t[k - i]] = nums[idx[k]];
        }
        i = j;
    }
    return ans;
}
