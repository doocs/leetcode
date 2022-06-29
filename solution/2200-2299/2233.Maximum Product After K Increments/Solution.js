/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var maximumProduct = function (nums, k) {
    const n = nums.length;
    let pq = new MinPriorityQueue();
    for (let i = 0; i < n; i++) {
        pq.enqueue(nums[i]);
    }
    for (let i = 0; i < k; i++) {
        pq.enqueue(pq.dequeue().element + 1);
    }
    let ans = 1;
    const limit = 10 ** 9 + 7;
    for (let i = 0; i < n; i++) {
        ans = (ans * pq.dequeue().element) % limit;
    }
    return ans;
};
