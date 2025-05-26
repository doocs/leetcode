function maxRemoval(nums: number[], queries: number[][]): number {
    queries.sort((a, b) => a[0] - b[0]);
    const pq = new MaxPriorityQueue<number>();
    const n = nums.length;
    const d: number[] = Array(n + 1).fill(0);
    let [s, j] = [0, 0];
    for (let i = 0; i < n; i++) {
        s += d[i];
        while (j < queries.length && queries[j][0] <= i) {
            pq.enqueue(queries[j][1]);
            j++;
        }
        while (s < nums[i] && !pq.isEmpty() && pq.front() >= i) {
            s++;
            d[pq.dequeue() + 1]--;
        }
        if (s < nums[i]) {
            return -1;
        }
    }
    return pq.size();
}
