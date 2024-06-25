function sortEvenOdd(nums: number[]): number[] {
    const n = nums.length;
    const a: number[] = [];
    const b: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (i % 2 === 0) {
            a.push(nums[i]);
        } else {
            b.push(nums[i]);
        }
    }
    a.sort((x, y) => x - y);
    b.sort((x, y) => y - x);
    const ans: number[] = [];
    for (let i = 0, j = 0; j < a.length; i += 2, ++j) {
        ans[i] = a[j];
    }
    for (let i = 1, j = 0; j < b.length; i += 2, ++j) {
        ans[i] = b[j];
    }
    return ans;
}
