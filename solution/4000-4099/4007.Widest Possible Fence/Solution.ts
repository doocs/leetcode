function maximumWidth(planks: number[]): number {
    const cnt = new Map<number, number>();
    for (const x of planks) {
        cnt.set(x, (cnt.get(x) ?? 0) + 1);
    }

    const t = new Map<number, number>();
    let ans = 0;

    for (const [x, v1] of cnt) {
        t.set(x, (t.get(x) ?? 0) + v1);
        ans = Math.max(ans, t.get(x)!);

        t.set(x * 2, (t.get(x * 2) ?? 0) + Math.floor(v1 / 2));
        ans = Math.max(ans, t.get(x * 2)!);

        for (const [y, v2] of cnt) {
            if (y > x) {
                const key = x + y;
                t.set(key, (t.get(key) ?? 0) + Math.min(v1, v2));
                ans = Math.max(ans, t.get(key)!);
            }
        }
    }

    return ans;
}
