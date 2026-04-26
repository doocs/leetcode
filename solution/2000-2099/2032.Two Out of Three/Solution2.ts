function twoOutOfThree(nums1: number[], nums2: number[], nums3: number[]): number[] {
    const mask = new Map<number, number>();
    const all = [nums1, nums2, nums3];

    all.forEach((nums, i) => {
        for (const x of nums) {
            mask.set(x, (mask.get(x) || 0) | (1 << i));
        }
    });

    return Array.from(mask.entries())
        .filter(([_, m]) => (m & (m - 1)) !== 0)
        .map(([x, _]) => x);
}
