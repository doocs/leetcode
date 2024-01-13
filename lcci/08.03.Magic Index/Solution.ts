function findMagicIndex(nums: number[]): number {
    const n = nums.length;
    const find = (l: number, r: number): number => {
        if (l > r || nums[r] < 0) {
            return -1;
        }
        const mid = l + Math.floor((r - l) / 2);
        if (nums[mid] >= l) {
            const res = find(l, mid - 1);
            if (res !== -1) {
                return res;
            }
        }
        if (nums[mid] === mid) {
            return mid;
        }
        return find(mid + 1, r);
    };
    return find(0, n - 1);
}
