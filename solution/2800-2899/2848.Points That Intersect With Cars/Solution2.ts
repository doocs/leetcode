function numberOfPoints(nums: number[][]): number {
    const d = new Map<number, number>();
    for (const [start, end] of nums) {
        d.set(start, (d.get(start) || 0) + 1);
        d.set(end + 1, (d.get(end + 1) || 0) - 1);
    }
    const keys = [...d.keys()].sort((a, b) => a - b);
    let [ans, s, last] = [0, 0, 0];
    for (const cur of keys) {
        if (s > 0) {
            ans += cur - last;
        }
        s += d.get(cur)!;
        last = cur;
    }
    return ans;
}
