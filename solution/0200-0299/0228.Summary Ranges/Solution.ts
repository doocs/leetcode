function summaryRanges(nums: number[]): string[] {
    const f = (i: number, j: number): string => {
        return i === j ? `${nums[i]}` : `${nums[i]}->${nums[j]}`;
    };
    const n = nums.length;
    const ans: string[] = [];
    for (let i = 0, j = 0; i < n; i = j + 1) {
        j = i;
        while (j + 1 < n && nums[j + 1] === nums[j] + 1) {
            ++j;
        }
        ans.push(f(i, j));
    }
    return ans;
}
