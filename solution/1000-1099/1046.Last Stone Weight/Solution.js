/**
 * @param {number[]} stones
 * @return {number}
 */
var lastStoneWeight = function (stones) {
    const pq = new MaxPriorityQueue();
    for (const v of stones) {
        pq.enqueue(v);
    }
    while (pq.size() > 1) {
        const x = pq.dequeue()['priority'];
        const y = pq.dequeue()['priority'];
        if (x != y) {
            pq.enqueue(x - y);
        }
    }
    return pq.isEmpty() ? 0 : pq.dequeue()['priority'];
};
