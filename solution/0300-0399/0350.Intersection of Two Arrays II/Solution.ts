function intersect(nums1: number[], nums2: number[]): number[] {
    const map = new Map<number, number>();
    for (const num of nums1) {
        map.set(num, (map.get(num) ?? 0) + 1);
    }

    const res = [];
    for (const num of nums2) {
        if (map.has(num) && map.get(num) !== 0) {
            res.push(num);
            map.set(num, map.get(num) - 1);
        }
    }
    return res;
}
