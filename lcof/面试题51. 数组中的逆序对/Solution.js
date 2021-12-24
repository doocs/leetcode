/**
 * @param {number[]} nums
 * @return {number}
 */
var reversePairs = function (nums) {
    const mergeSort = (nums, left, right) => {
        if (left >= right) {
            return 0;
        }
        const mid = (left + right) >> 1;
        let res = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);
        let i = left;
        let j = mid + 1;
        let tmp = [];
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp.push(nums[i++]);
            } else {
                tmp.push(nums[j++]);
                res += mid - i + 1;
            }
        }
        while (i <= mid) {
            tmp.push(nums[i++]);
        }
        while (j <= right) {
            tmp.push(nums[j++]);
        }
        for (i = left, j = 0; i <= right; ++i, ++j) {
            nums[i] = tmp[j];
        }
        return res;
    };

    return mergeSort(nums, 0, nums.length - 1);
};
