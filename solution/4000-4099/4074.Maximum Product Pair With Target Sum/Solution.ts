function maxProductPair(nums: number[], target: number): number[] {
    const pos = new Map<number, number>();
    let mx = -Infinity;
    let ans = [-1, -1];
    for (let i = 0; i < nums.length; ++i) {
        const x = nums[i],
            y = target - x;
        if (x !== y && x * y > mx && pos.has(y)) {
            const j = pos.get(y)!;
            mx = x * y;
            ans = x > y ? [i, j] : [j, i];
        }
        pos.set(x, i);
    }
    return ans;
}
