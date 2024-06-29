function findKthLargest(nums: number[], k: number): number {
    const maxPQ = new MaxPriorityQueue();
    for (const x of nums) {
        maxPQ.enqueue(x);
    }

    let res = 0;
    while (k--) res = maxPQ.dequeue().element;

    return res;
}
