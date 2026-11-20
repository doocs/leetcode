function maxSelectedElements(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let ans = 1;
    let prev = -Infinity;
    let f0 = 1;
    let f1 = 1;
    for (const x of nums) {
        if (x === prev) {
            f1 = f0 + 1;
        } else if (x === prev + 1) {
            ++f0;
            ++f1;
        } else if (x === prev + 2) {
            f0 = f1 + 1;
            f1 = 1;
        } else {
            f0 = 1;
            f1 = 1;
        }
        ans = Math.max(ans, f0, f1);
        prev = x;
    }
    return ans;
}
