function getFinalState(nums: number[], k: number, multiplier: number): number[] {
    const pq = new PriorityQueue({
        compare: (i, j) => (nums[i] === nums[j] ? i - j : nums[i] - nums[j]),
    });
    for (let i = 0; i < nums.length; ++i) {
        pq.enqueue(i);
    }
    while (k--) {
        const i = pq.dequeue()!;
        nums[i] *= multiplier;
        pq.enqueue(i);
    }
    return nums;
}
