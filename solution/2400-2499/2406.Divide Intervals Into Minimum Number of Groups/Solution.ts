function minGroups(intervals: number[][]): number {
    intervals.sort((a, b) => a[0] - b[0]);
    const q = new PriorityQueue({ compare: (a, b) => a - b });
    for (const [l, r] of intervals) {
        if (!q.isEmpty() && q.front() < l) {
            q.dequeue();
        }
        q.enqueue(r);
    }
    return q.size();
}
