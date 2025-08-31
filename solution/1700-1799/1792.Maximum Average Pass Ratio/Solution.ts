function maxAverageRatio(classes: number[][], extraStudents: number): number {
    function calcGain(a: number, b: number): number {
        return (a + 1) / (b + 1) - a / b;
    }
    const pq = new PriorityQueue<[number, number]>(
        (p, q) => calcGain(q[0], q[1]) - calcGain(p[0], p[1]),
    );
    for (const [a, b] of classes) {
        pq.enqueue([a, b]);
    }
    while (extraStudents-- > 0) {
        const item = pq.dequeue();
        const [a, b] = item;
        pq.enqueue([a + 1, b + 1]);
    }
    let ans = 0;
    while (!pq.isEmpty()) {
        const item = pq.dequeue()!;
        const [a, b] = item;
        ans += a / b;
    }
    return ans / classes.length;
}
