function arrayChange(nums: number[], operations: number[][]): number[] {
    const n = nums.length;
    let hashMap = new Map(nums.map((v, i) => [v, i]));
    for (let [oldVal, newVal] of operations) {
        let idx = hashMap.get(oldVal);
        hashMap.delete(oldVal);
        hashMap.set(newVal, idx);
    }
    let ans = new Array(n);
    for (let [val, key] of hashMap.entries()) {
        ans[key] = val;
    }
    return ans;
}
