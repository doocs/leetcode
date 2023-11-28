function minBuildTime(blocks: number[], split: number): number {
    const pq = new MinPriorityQueue();
    for (const x of blocks) {
        pq.enqueue(x);
    }
    while (pq.size() > 1) {
        pq.dequeue()!;
        pq.enqueue(pq.dequeue()!.element + split);
    }
    return pq.dequeue()!.element;
}
