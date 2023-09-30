function minLengthAfterRemovals(nums: number[]): number {
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) ?? 0) + 1);
    }
    const pq = new MaxPriorityQueue();
    for (const [_, v] of cnt) {
        pq.enqueue(v);
    }
    let ans = nums.length;
    while (pq.size() > 1) {
        let x = pq.dequeue().element;
        let y = pq.dequeue().element;
        if (--x > 0) {
            pq.enqueue(x);
        }
        if (--y > 0) {
            pq.enqueue(y);
        }
        ans -= 2;
    }
    return ans;
}
