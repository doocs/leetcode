function scheduleCourse(courses: number[][]): number {
    courses.sort((a, b) => a[1] - b[1]);
    const pq = new MaxPriorityQueue<number>();
    let s = 0;
    for (const [duration, last] of courses) {
        pq.enqueue(duration);
        s += duration;
        while (s > last) {
            s -= pq.dequeue();
        }
    }
    return pq.size();
}
