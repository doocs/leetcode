function rearrangeArray(nums: number[]): number[] {
    const ans: number[] = Array(nums.length);
    let [i, j] = [0, 1];
    for (const x of nums) {
        if (x > 0) {
            ans[i] = x;
            i += 2;
        } else {
            ans[j] = x;
            j += 2;
        }
    }
    return ans;
}
