function rearrangeArray(nums: number[]): number[] {
    let ans = [];
    let i = 0,
        j = 1;
    for (let num of nums) {
        if (num > 0) {
            ans[i] = num;
            i += 2;
        } else {
            ans[j] = num;
            j += 2;
        }
    }
    return ans;
}
