function distinctNumbers(nums: number[], k: number): number[] {
    const m = Math.max(...nums);
    const cnt: number[] = Array(m + 1).fill(0);
    let v: number = 0;
    for (let i = 0; i < k; ++i) {
        if (++cnt[nums[i]] === 1) {
            v++;
        }
    }
    const ans: number[] = [v];
    for (let i = k; i < nums.length; ++i) {
        if (++cnt[nums[i]] === 1) {
            v++;
        }
        if (--cnt[nums[i - k]] === 0) {
            v--;
        }
        ans.push(v);
    }
    return ans;
}
