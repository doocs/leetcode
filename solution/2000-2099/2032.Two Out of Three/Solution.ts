function twoOutOfThree(
    nums1: number[],
    nums2: number[],
    nums3: number[],
): number[] {
    const count = new Array(101).fill(0);
    new Set(nums1).forEach(v => count[v]++);
    new Set(nums2).forEach(v => count[v]++);
    new Set(nums3).forEach(v => count[v]++);
    const ans = [];
    count.forEach((v, i) => {
        if (v >= 2) {
            ans.push(i);
        }
    });
    return ans;
}
