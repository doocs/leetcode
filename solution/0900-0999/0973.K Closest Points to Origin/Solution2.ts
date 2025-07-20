function kClosest(points: number[][], k: number): number[][] {
    const maxQ = new MaxPriorityQueue<{ point: number[]; dist: number }>(entry => entry.dist);
    for (const [x, y] of points) {
        const dist = x * x + y * y;
        maxQ.enqueue({ point: [x, y], dist });
        if (maxQ.size() > k) {
            maxQ.dequeue();
        }
    }
    return maxQ.toArray().map(entry => entry.point);
}
