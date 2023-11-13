function minOperations(nums1: number[], nums2: number[]): number {
    const n = nums1.length;
    const f = (x: number, y: number): number => {
        let cnt = 0;
        for (let i = 0; i < n - 1; ++i) {
            if (nums1[i] <= x && nums2[i] <= y) {
                continue;
            }
            if (!(nums1[i] <= y && nums2[i] <= x)) {
                return -1;
            }
            ++cnt;
        }
        return cnt;
    };
    const a = f(nums1.at(-1), nums2.at(-1));
    const b = f(nums2.at(-1), nums1.at(-1));
    return a + b === -2 ? -1 : Math.min(a, b + 1);
}
