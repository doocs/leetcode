function maxWidthOfVerticalArea(points: number[][]): number {
    const nums: number[] = points.map(point => point[0]);
    const inf = 1 << 30;
    const n = nums.length;
    let mi = inf;
    let mx = -inf;
    for (const x of nums) {
        mi = Math.min(mi, x);
        mx = Math.max(mx, x);
    }
    const bucketSize = Math.max(1, Math.floor((mx - mi) / (n - 1)));
    const bucketCount = Math.floor((mx - mi) / bucketSize) + 1;
    const buckets = new Array(bucketCount).fill(0).map(() => [inf, -inf]);
    for (const x of nums) {
        const i = Math.floor((x - mi) / bucketSize);
        buckets[i][0] = Math.min(buckets[i][0], x);
        buckets[i][1] = Math.max(buckets[i][1], x);
    }
    let prev = inf;
    let ans = 0;
    for (const [left, right] of buckets) {
        if (left > right) {
            continue;
        }
        ans = Math.max(ans, left - prev);
        prev = right;
    }
    return ans;
}
