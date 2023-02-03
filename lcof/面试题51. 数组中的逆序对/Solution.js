/**
 * @param {number[]} nums
 * @return {number}
 */
var reversePairs = function (nums) {
    const mergeSort = (l, r) => {
        if (l >= r) {
            return 0;
        }
        const mid = (l + r) >> 1;
        let ans = mergeSort(l, mid) + mergeSort(mid + 1, r);
        let i = l;
        let j = mid + 1;
        let t = [];
        while (i <= mid && j <= r) {
            if (nums[i] <= nums[j]) {
                t.push(nums[i++]);
            } else {
                ans += mid - i + 1;
                t.push(nums[j++]);
            }
        }
        while (i <= mid) {
            t.push(nums[i++]);
        }
        while (j <= r) {
            t.push(nums[j++]);
        }
        for (i = l; i <= r; ++i) {
            nums[i] = t[i - l];
        }
        return ans;
    };
    return mergeSort(0, nums.length - 1);
};
