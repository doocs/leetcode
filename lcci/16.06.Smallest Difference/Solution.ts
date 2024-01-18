function smallestDifference(a: number[], b: number[]): number {
    b.sort((a, b) => a - b);
    let ans = Infinity;
    const search = (nums: number[], x: number): number => {
        let [l, r] = [0, nums.length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (const x of a) {
        const j = search(b, x);
        if (j < b.length) {
            ans = Math.min(ans, b[j] - x);
        }
        if (j > 0) {
            ans = Math.min(ans, x - b[j - 1]);
        }
    }
    return ans;
}
