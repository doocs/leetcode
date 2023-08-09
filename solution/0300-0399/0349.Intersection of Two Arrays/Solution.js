/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
var intersection = function (nums1, nums2) {
    const s = Array(1001).fill(false);
    for (const x of nums1) {
        s[x] = true;
    }
    const ans = [];
    for (const x of nums2) {
        if (s[x]) {
            ans.push(x);
            s[x] = false;
        }
    }
    return ans;
};
