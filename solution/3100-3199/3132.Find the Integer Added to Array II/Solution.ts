function minimumAddedInteger(nums1: number[], nums2: number[]): number {
    nums1.sort((a, b) => a - b);
    nums2.sort((a, b) => a - b);
    const f = (x: number): boolean => {
        let [i, j, cnt] = [0, 0, 0];
        while (i < nums1.length && j < nums2.length) {
            if (nums2[j] - nums1[i] !== x) {
                ++cnt;
            } else {
                ++j;
            }
            ++i;
        }
        return cnt <= 2;
    };
    let ans = Infinity;
    for (let i = 0; i < 3; ++i) {
        const x = nums2[0] - nums1[i];
        if (f(x)) {
            ans = Math.min(ans, x);
        }
    }
    return ans;
}
