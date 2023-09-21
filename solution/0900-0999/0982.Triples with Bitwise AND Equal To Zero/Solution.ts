function countTriplets(nums: number[]): number {
    const mx = Math.max(...nums);
    const cnt: number[] = Array(mx + 1).fill(0);
    for (const x of nums) {
        for (const y of nums) {
            cnt[x & y]++;
        }
    }
    let ans = 0;
    for (let xy = 0; xy <= mx; ++xy) {
        for (const z of nums) {
            if ((xy & z) === 0) {
                ans += cnt[xy];
            }
        }
    }
    return ans;
}
