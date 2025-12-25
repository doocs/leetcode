/**
 * @param {number} k
 * @param {number[]} nums
 */
var KthLargest = function (k, nums) {
    this.k = k;
    this.minQ = new MinPriorityQueue();
    for (const x of nums) {
        this.add(x);
    }
};

/**
 * @param {number} val
 * @return {number}
 */
KthLargest.prototype.add = function (val) {
    this.minQ.enqueue(val);
    if (this.minQ.size() > this.k) {
        this.minQ.dequeue();
    }
    return this.minQ.front();
};

/**
 * Your KthLargest object will be instantiated and called as such:
 * var obj = new KthLargest(k, nums)
 * var param_1 = obj.add(val)
 */
