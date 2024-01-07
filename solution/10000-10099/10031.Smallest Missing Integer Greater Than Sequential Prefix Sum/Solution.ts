function missingInteger(nums: number[]): number {
    let [s, j] = [nums[0], 1];
    const n = nums.length;
    while (j < n && nums[j] === nums[j - 1] + 1) {
        s += nums[j++];
    }
    const vis: boolean[] = Array(51).fill(false);
    for (const x of nums) {
        vis[x] = true;
    }
    for (let x = s; ; ++x) {
        if (x >= vis.length || !vis[x]) {
            return x;
        }
    }
}
