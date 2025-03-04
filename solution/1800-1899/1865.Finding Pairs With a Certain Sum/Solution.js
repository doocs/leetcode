/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 */
var FindSumPairs = function (nums1, nums2) {
    this.nums1 = nums1;
    this.nums2 = nums2;
    this.cnt = new Map();
    for (const x of nums2) {
        this.cnt.set(x, (this.cnt.get(x) || 0) + 1);
    }
};

/**
 * @param {number} index
 * @param {number} val
 * @return {void}
 */
FindSumPairs.prototype.add = function (index, val) {
    const old = this.nums2[index];
    this.cnt.set(old, this.cnt.get(old) - 1);
    this.nums2[index] += val;
    const now = this.nums2[index];
    this.cnt.set(now, (this.cnt.get(now) || 0) + 1);
};

/**
 * @param {number} tot
 * @return {number}
 */
FindSumPairs.prototype.count = function (tot) {
    return this.nums1.reduce((acc, x) => acc + (this.cnt.get(tot - x) || 0), 0);
};

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * var obj = new FindSumPairs(nums1, nums2)
 * obj.add(index,val)
 * var param_2 = obj.count(tot)
 */
