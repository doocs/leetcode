function shortestSubarray(nums, k) {
    const [n, MAX] = [nums.length, Number.POSITIVE_INFINITY];
    const s = Array(n + 1).fill(0);
    const q = [];
    let ans = MAX;

    for (let i = 0; i < n; i++) {
        s[i + 1] = s[i] + nums[i];
    }

    for (let i = 0; i < n + 1; i++) {
        while (q.length && s[i] - s[q[0]] >= k) {
            ans = Math.min(ans, i - q.shift());
        }

        while (q.length && s[i] <= s[q.at(-1)]) {
            q.pop();
        }

        q.push(i);
    }

    return ans === MAX ? -1 : ans;
}
