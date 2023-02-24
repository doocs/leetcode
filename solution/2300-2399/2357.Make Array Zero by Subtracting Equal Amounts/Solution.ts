function minimumOperations(nums: number[]): number {
    const s = new Array(101).fill(false);
    s[0] = true;
    let ans = 0;
    for (const x of nums) {
        if (!s[x]) {
            s[x] = true;
            ++ans;
        }
    }
    return ans;
}
