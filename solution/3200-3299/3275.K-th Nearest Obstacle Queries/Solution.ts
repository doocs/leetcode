function resultsArray(queries: number[][], k: number): number[] {
    const pq = new MaxPriorityQueue<number>();
    const ans: number[] = [];
    for (const [x, y] of queries) {
        pq.enqueue(Math.abs(x) + Math.abs(y));
        if (pq.size() > k) {
            pq.dequeue();
        }
        ans.push(pq.size() === k ? pq.front() : -1);
    }
    return ans;
}
