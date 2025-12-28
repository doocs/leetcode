function numberGame(nums: number[]): number[] {
    const pq = new MinPriorityQueue<number>();
    for (const x of nums) {
        pq.enqueue(x);
    }
    const ans: number[] = [];
    while (pq.size()) {
        const a = pq.dequeue();
        const b = pq.dequeue();
        ans.push(b, a);
    }
    return ans;
}
