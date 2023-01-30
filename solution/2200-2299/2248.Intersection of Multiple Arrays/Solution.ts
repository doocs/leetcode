function intersection(nums: number[][]): number[] {
    const cnt = new Array(1001).fill(0);
    for (const arr of nums) {
        for (const x of arr) {
            cnt[x]++;
        }
    }
    const ans: number[] = [];
    for (let x = 0; x < 1001; x++) {
        if (cnt[x] === nums.length) {
            ans.push(x);
        }
    }
    return ans;
}
