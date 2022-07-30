/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var wiggleSort = function (nums) {
    let bucket = new Array(5001).fill(0);
    for (const v of nums) {
        bucket[v]++;
    }
    const n = nums.length;
    let j = 5000;
    for (let i = 1; i < n; i += 2) {
        while (bucket[j] == 0) {
            --j;
        }
        nums[i] = j;
        --bucket[j];
    }
    for (let i = 0; i < n; i += 2) {
        while (bucket[j] == 0) {
            --j;
        }
        nums[i] = j;
        --bucket[j];
    }
};
