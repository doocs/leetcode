function maxKelements(nums: number[], k: number): number {
    const pq = new MaxPriorityQueue<number>();
    nums.forEach(num => pq.enqueue(num));
    let ans = 0;
    while (k > 0) {
        const v = pq.dequeue();
        ans += v;
        pq.enqueue(Math.floor((v + 2) / 3));
        k--;
    }
    return ans;
}
