function findLeastNumOfUniqueInts(arr: number[], k: number): number {
    const cnt: Map<number, number> = new Map();
    for (const x of arr) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    const nums: number[] = [];
    for (const [_, v] of cnt) {
        nums.push(v);
    }
    nums.sort((a, b) => a - b);
    for (let i = 0; i < nums.length; ++i) {
        k -= nums[i];
        if (k < 0) {
            return nums.length - i;
        }
    }
    return 0;
}
