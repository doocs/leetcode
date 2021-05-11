/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number[]}
 */
var maxSlidingWindow = function (nums, k) {
    let len = nums.length;
    if (len < k) return [];
    let res = [], win = [];
    for (let i = 0; i < k; i++) {
        while (win.length > 0 && nums[i] >= nums[win[win.length - 1]])
            win.pop();
        win.push(i);
    }
    res.push(nums[win[0]]);
    for (let i = k; i < len; i++) {
        while (win.length > 0 && nums[i] >= nums[win[win.length - 1]])
            win.pop();
        if (win.length > 0 && win[0] < i - k + 1)
            win.shift();
        win.push(i);
        res.push(nums[win[0]]);
    }
    return res;
};