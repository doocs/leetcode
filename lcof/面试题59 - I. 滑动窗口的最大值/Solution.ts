function maxSlidingWindow(nums: number[], k: number): number[] {
    const n = nums.length;
    const res = [];
    if (n === 0 || k === 0) {
        return res;
    }
    const queue = [];
    for (let i = 0; i < k; i++) {
        while (queue.length !== 0 && queue[queue.length - 1] < nums[i]) {
            queue.pop();
        }
        queue.push(nums[i]);
    }
    res.push(queue[0]);
    for (let i = k; i < n; i++) {
        if (queue[0] === nums[i - k]) {
            queue.shift();
        }
        while (queue.length !== 0 && queue[queue.length - 1] < nums[i]) {
            queue.pop();
        }
        queue.push(nums[i]);
        res.push(queue[0]);
    }
    return res;
}
