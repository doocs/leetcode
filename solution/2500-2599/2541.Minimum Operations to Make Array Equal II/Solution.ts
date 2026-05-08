function minOperations(nums1: number[], nums2: number[], k: number): number {
    let [a, b] = [0, 0];
    for (let i = 0; i < nums1.length; ++i) {
        const [x, y] = [nums1[i], nums2[i]];
        if (x === y) {
            continue;
        }
        if (k === 0 || (x - y) % k !== 0) {
            return -1;
        }
        const t = (x - y) / k;
        if (t < 0) {
            a += -t;
        } else {
            b += t;
        }
    }
    return a === b ? a : -1;
}
