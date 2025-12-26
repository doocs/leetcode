/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var maximumProduct = function (nums, k) {
    const pq = new MinPriorityQueue();
    nums.forEach(x => pq.enqueue(x));
    while (k--) {
        const x = pq.dequeue();
        pq.enqueue(x + 1);
    }
    let ans = 1;
    const mod = 10 ** 9 + 7;
    while (!pq.isEmpty()) {
        ans = (ans * pq.dequeue()) % mod;
    }
    return ans;
};
