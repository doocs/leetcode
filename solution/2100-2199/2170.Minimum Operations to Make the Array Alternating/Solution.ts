function minimumOperations(nums: number[]): number {
    const n = nums.length,
        m = 10 ** 5;
    let odd = new Array(m).fill(0);
    let even = new Array(m).fill(0);
    for (let i = 0; i < n; i++) {
        let cur = nums[i];
        if (i & 1) {
            odd[cur] = (odd[cur] || 0) + 1;
        } else {
            even[cur] = (even[cur] || 0) + 1;
        }
    }
    let i1 = odd.indexOf(Math.max(...odd));
    let i2 = even.indexOf(Math.max(...even));
    if (i1 != i2) {
        return n - odd[i1] - even[i2];
    } else {
        let l1 = odd[i1],
            l2 = even[i2];
        (odd[i1] = 0), (even[i2] = 0);
        let j1 = odd.indexOf(Math.max(...odd));
        let j2 = even.indexOf(Math.max(...even));
        return n - Math.max(l1 + even[j2], l2 + odd[j1]);
    }
}
