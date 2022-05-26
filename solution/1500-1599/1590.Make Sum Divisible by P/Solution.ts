function minSubarray(nums: number[], p: number): number {
    const n = nums.length;
    let mod = 0;
    for (let i = 0; i < n; i++) {
        mod = (nums[i] + mod) % p;
    }
    if (!mod) return 0;

    let hashMap = new Map<number, number>();
    hashMap.set(0, -1);
    let ans = n;
    let subMod = 0;
    for (let i = 0; i < n; i++) {
        let cur = nums[i];
        subMod = (subMod + cur) % p;
        let target = (subMod - mod + p) % p;
        if (hashMap.has(target)) {
            let j = hashMap.get(target);
            ans = Math.min(i - j, ans);
            if (ans == 1 && ans != n) {
                return ans;
            }
        }
        hashMap.set(subMod, i);
    }
    return ans == n ? -1 : ans;
}
