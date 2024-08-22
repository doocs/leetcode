function maximumDifference(nums: number[]): number {
    let [ans, mi] = [-1, Infinity];
    for (const x of nums) {
        if (x > mi) {
            ans = Math.max(ans, x - mi);
        } else {
            mi = x;
        }
    }
    return ans;
}
