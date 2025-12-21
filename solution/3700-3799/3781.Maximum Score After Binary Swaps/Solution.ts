function maximumScore(nums: number[], s: string): number {
    let ans = 0;
    const pq = new MaxPriorityQueue<number>();

    for (let i = 0; i < nums.length; i++) {
        const x = nums[i];
        const c = s[i];
        pq.enqueue(x);
        if (c === '1') {
            ans += pq.dequeue()!;
        }
    }

    return ans;
}
