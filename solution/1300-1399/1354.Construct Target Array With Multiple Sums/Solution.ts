function isPossible(target: number[]): boolean {
    const pq = new MaxPriorityQueue<number>();
    let s = 0;
    for (const x of target) {
        s += x;
        pq.enqueue(x);
    }
    while (pq.front() > 1) {
        const mx = pq.dequeue();
        const t = s - mx;
        if (t < 1 || mx - t < 1) {
            return false;
        }
        const x = mx % t || t;
        pq.enqueue(x);
        s = s - mx + x;
    }
    return true;
}
