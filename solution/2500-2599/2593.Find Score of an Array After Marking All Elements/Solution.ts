interface pair {
    x: number;
    i: number;
}

function findScore(nums: number[]): number {
    const q = new PriorityQueue({
        compare: (a: pair, b: pair) => (a.x === b.x ? a.i - b.i : a.x - b.x),
    });
    const n = nums.length;
    const vis: boolean[] = new Array(n).fill(false);
    for (let i = 0; i < n; ++i) {
        q.enqueue({ x: nums[i], i });
    }
    let ans = 0;
    while (!q.isEmpty()) {
        const { x, i } = q.dequeue()!;
        if (vis[i]) {
            continue;
        }
        ans += x;
        vis[i] = true;
        if (i - 1 >= 0) {
            vis[i - 1] = true;
        }
        if (i + 1 < n) {
            vis[i + 1] = true;
        }
        while (!q.isEmpty() && vis[q.front()!.i]) {
            q.dequeue();
        }
    }
    return ans;
}
