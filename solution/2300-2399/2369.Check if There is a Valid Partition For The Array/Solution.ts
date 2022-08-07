function validPartition(nums: number[]): boolean {
    const n = nums.length;
    const vis = new Array(n).fill(false);
    const queue = [0];
    while (queue.length !== 0) {
        const i = queue.shift() ?? 0;

        if (i === n) {
            return true;
        }

        if (!vis[i + 2] && i + 2 <= n && nums[i] === nums[i + 1]) {
            queue.push(i + 2);
            vis[i + 2] = true;
        }

        if (
            !vis[i + 3] &&
            i + 3 <= n &&
            ((nums[i] === nums[i + 1] && nums[i + 1] === nums[i + 2]) ||
                (nums[i] === nums[i + 1] - 1 &&
                    nums[i + 1] === nums[i + 2] - 1))
        ) {
            queue.push(i + 3);
            vis[i + 3] = true;
        }
    }
    return false;
}
