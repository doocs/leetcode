function findMaxK(nums: number[]): number {
    const set = new Set(nums);
    let res = -1;
    for (const num of set) {
        if (set.has(-num)) {
            res = Math.max(num, res);
        }
    }
    return res;
}
