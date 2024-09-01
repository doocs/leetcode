function resultsArray(queries: number[][], k: number): number[] {
    const pq = new MaxPriorityQueue();
    const ans: number[] = [];
    for (const [x, y] of queries) {
        pq.enqueue(Math.abs(x) + Math.abs(y));
        if (pq.size() > k) {
            pq.dequeue();
        }
        ans.push(pq.size() === k ? pq.front().element : -1);
    }
    return ans;
}
