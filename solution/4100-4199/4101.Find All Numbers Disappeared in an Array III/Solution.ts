function findDisappearedNumbers(nums: number[], lower: number, upper: number): number[][] {
    nums.sort((a, b) => a - b);
    const l = _.sortedIndex(nums, lower);
    const r = _.sortedIndex(nums, upper + 1);
    const ans: number[][] = [];
    let pre = lower;

    for (let i = l; i < r; i++) {
        const cur = nums[i];
        if (pre < cur) {
            ans.push([pre, cur - 1]);
        }
        pre = cur + 1;
    }

    if (pre <= upper) {
        ans.push([pre, upper]);
    }
    return ans;
}
