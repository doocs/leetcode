function intersection(nums: number[][]): number[] {
    const n = nums.length;
    let ans = nums[0];
    for (let i = 1; i < n && ans.length; i++) {
        const cur = new Set(nums[i]);
        // get intersect
        ans = ans.filter(v => cur.has(v));
    }
    return ans.sort((a, b) => a - b);
}
