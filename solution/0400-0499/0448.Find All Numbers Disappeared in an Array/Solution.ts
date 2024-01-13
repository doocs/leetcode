function findDisappearedNumbers(nums: number[]): number[] {
    const n = nums.length;
    const s: boolean[] = new Array(n + 1).fill(false);
    for (const x of nums) {
        s[x] = true;
    }
    const ans: number[] = [];
    for (let i = 1; i <= n; ++i) {
        if (!s[i]) {
            ans.push(i);
        }
    }
    return ans;
}
