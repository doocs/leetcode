/**
 * @param {number[]} stones
 * @return {number}
 */
var lastStoneWeight = function (stones) {
    const pq = new MaxPriorityQueue();
    for (const x of stones) {
        pq.enqueue(x);
    }
    while (pq.size() > 1) {
        const y = pq.dequeue()['priority'];
        const x = pq.dequeue()['priority'];
        if (x != y) {
            pq.enqueue(y - x);
        }
    }
    return pq.isEmpty() ? 0 : pq.dequeue()['priority'];
};
