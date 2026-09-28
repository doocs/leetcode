function rearrangeArray(nums: number[]): number[] {
    const mx = Math.max(...nums);
    const cnt = new Array(mx + 1).fill(0);

    for (const x of nums) {
        cnt[x]++;
    }

    const ans: number[] = [];
    while (ans.length < nums.length) {
        for (let x = 1; x <= mx; x++) {
            if (cnt[x]) {
                ans.push(x);
                cnt[x]--;
            }
        }
    }
    return ans;
}
