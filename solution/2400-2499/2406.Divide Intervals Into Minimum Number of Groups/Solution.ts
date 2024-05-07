function minGroups(intervals: number[][]): number {
    intervals.sort((a, b) => a[0] - b[0]);
    const q = new PriorityQueue({ compare: (a, b) => a - b });
    for (const [left, right] of intervals) {
        if (!q.isEmpty() && q.front() < left) {
            q.dequeue();
        }
        q.enqueue(right);
    }
    return q.size();
}
