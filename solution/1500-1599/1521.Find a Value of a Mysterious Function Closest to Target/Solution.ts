function closestToTarget(arr: number[], target: number): number {
    let ans = Math.abs(arr[0] - target);
    let pre = new Set<number>();
    pre.add(arr[0]);
    for (const x of arr) {
        const cur = new Set<number>();
        cur.add(x);
        for (const y of pre) {
            cur.add(x & y);
        }
        for (const y of cur) {
            ans = Math.min(ans, Math.abs(y - target));
        }
        pre = cur;
    }
    return ans;
}
