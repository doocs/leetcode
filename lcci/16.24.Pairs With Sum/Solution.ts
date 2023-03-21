function pairSums(nums: number[], target: number): number[][] {
    const cnt = new Map();
    const ans: number[][] = [];
    for (const x of nums) {
        const y = target - x;
        if (cnt.has(y)) {
            ans.push([x, y]);
            const yCount = cnt.get(y) - 1;
            if (yCount === 0) {
                cnt.delete(y);
            } else {
                cnt.set(y, yCount);
            }
        } else {
            cnt.set(x, (cnt.get(x) || 0) + 1);
        }
    }
    return ans;
}
