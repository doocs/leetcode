function longestSubarray(nums: number[], limit: number): number {
    let ans = 0;
    let l = 0;
    const maxQueue: number[] = [];
    const minQueue: number[] = [];

    for (let r = 0; r < nums.length; r++) {
        while (maxQueue.length && nums[maxQueue.at(-1)!] < nums[r]) maxQueue.pop();
        while (minQueue.length && nums[minQueue.at(-1)!] > nums[r]) minQueue.pop();
        maxQueue.push(r);
        minQueue.push(r);

        const diff = nums[maxQueue[0]] - nums[minQueue[0]];

        if (diff <= limit) {
            ans = Math.max(ans, r - l + 1);
        } else {
            l++;

            if (maxQueue[0] < l) maxQueue.shift();
            if (minQueue[0] < l) minQueue.shift();
        }
    }

    return ans;
}
