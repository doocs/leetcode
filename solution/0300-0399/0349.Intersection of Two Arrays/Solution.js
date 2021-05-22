/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
 var intersection = function(nums1, nums2) {
    const s = new Set();
    for (const num of nums1) {
        s.add(num);
    }
    let res = new Set();
    for (const num of nums2) {
        if (s.has(num)) {
            res.add(num);
        }
    }
    return [...res];
};