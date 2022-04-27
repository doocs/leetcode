function findKthLargest(nums: number[], k: number): number {
    const n = nums.length;
    const swap = (i: number, j: number) => {
        [nums[i], nums[j]] = [nums[j], nums[i]];
    };
    const sort = (l: number, r: number) => {
        if (l + 1 > k || l >= r) {
            return;
        }
        swap(l, l + Math.floor(Math.random() * (r - l)));
        const num = nums[l];
        let mark = l;
        for (let i = l + 1; i < r; i++) {
            if (nums[i] > num) {
                mark++;
                swap(i, mark);
            }
        }
        swap(l, mark);

        sort(l, mark);
        sort(mark + 1, r);
    };
    sort(0, n);
    return nums[k - 1];
}
