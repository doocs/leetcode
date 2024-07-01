function findKthLargest(nums: number[], k: number): number {
    const minQ = new MinPriorityQueue();
    for (const x of nums) {
        minQ.enqueue(x);
        if (minQ.size() > k) {
            minQ.dequeue();
        }
    }
    return minQ.front().element;
}
