function decompressRLElist(nums: number[]): number[] {
    let n = nums.length >> 1;
    let ans = [];
    for (let i = 0; i < n; i++) {
        let freq = nums[2 * i],
            val = nums[2 * i + 1];
        ans.push(...new Array(freq).fill(val));
    }
    return ans;
}
