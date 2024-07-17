function findLonely(nums: number[]): number[] {
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    const ans: number[] = [];
    for (const [x, v] of cnt) {
        if (v === 1 && !cnt.has(x - 1) && !cnt.has(x + 1)) {
            ans.push(x);
        }
    }
    return ans;
}
