function numberGame(nums: number[]): number[] {
    const pq = new MinPriorityQueue();
    for (const x of nums) {
        pq.enqueue(x);
    }
    const ans: number[] = [];
    while (pq.size()) {
        const a = pq.dequeue().element;
        const b = pq.dequeue().element;
        ans.push(b, a);
    }
    return ans;
}
