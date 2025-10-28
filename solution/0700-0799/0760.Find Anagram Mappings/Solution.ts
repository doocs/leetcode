function anagramMappings(nums1: number[], nums2: number[]): number[] {
    const d: Map<number, number> = new Map();
    for (let i = 0; i < nums2.length; ++i) {
        d.set(nums2[i], i);
    }
    return nums1.map(num => d.get(num)!);
}
