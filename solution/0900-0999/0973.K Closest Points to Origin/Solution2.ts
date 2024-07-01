function kClosest(points: number[][], k: number): number[][] {
    const minPQ = new MinPriorityQueue();

    for (const [x, y] of points) {
        const d = x ** 2 + y ** 2;
        minPQ.enqueue([x, y], d);
    }

    const res: number[][] = [];
    while (k--) res.push(minPQ.dequeue().element);

    return res;
}
