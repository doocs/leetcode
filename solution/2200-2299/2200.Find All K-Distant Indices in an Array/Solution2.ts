function findKDistantIndices(nums: number[], key: number, k: number): number[] {
    const n = nums.length;
    const idx: number[] = [];
    for (let i = 0; i < n; i++) {
        if (nums[i] === key) {
            idx.push(i);
        }
    }
    const search = (x: number): number => {
        let [l, r] = [0, idx.length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (idx[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const ans: number[] = [];
    for (let i = 0; i < n; ++i) {
        const l = search(i - k);
        const r = search(i + k + 1) - 1;
        if (l <= r) {
            ans.push(i);
        }
    }
    return ans;
}
