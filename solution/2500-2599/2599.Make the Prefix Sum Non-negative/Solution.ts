function makePrefSumNonNegative(nums: number[]): number {
    const pq = new MinPriorityQueue();
    let ans = 0;
    let s = 0;
    for (const x of nums) {
        s += x;
        if (x < 0) {
            pq.enqueue(x);
        }
        while (s < 0) {
            s -= pq.dequeue().element;
            ++ans;
        }
    }
    return ans;
}
