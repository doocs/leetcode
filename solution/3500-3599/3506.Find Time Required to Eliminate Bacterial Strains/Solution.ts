function minEliminationTime(timeReq: number[], splitTime: number): number {
    const pq = new MinPriorityQueue<number>();
    for (const b of timeReq) {
        pq.enqueue(b);
    }
    while (pq.size() > 1) {
        pq.dequeue();
        pq.enqueue(pq.dequeue() + splitTime);
    }
    return pq.dequeue();
}
