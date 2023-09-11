function pickGifts(gifts: number[], k: number): number {
    const pq = new MaxPriorityQueue();
    for (const v of gifts) {
        pq.enqueue(v, v);
    }
    while (k--) {
        let v = pq.dequeue().element;
        v = Math.floor(Math.sqrt(v));
        pq.enqueue(v, v);
    }
    let ans = 0;
    while (!pq.isEmpty()) {
        ans += pq.dequeue().element;
    }
    return ans;
}
