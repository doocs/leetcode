function countTrapezoids(points: number[][]): number {
    const mod = 1_000_000_007;
    const cnt = new Map<number, number>();

    for (const p of points) {
        cnt.set(p[1], (cnt.get(p[1]) ?? 0) + 1);
    }

    let ans = 0;
    let s = 0;
    for (const v of cnt.values()) {
        const t = (v * (v - 1)) / 2;
        const mul = BigInt(s) * BigInt(t);
        ans = Number((BigInt(ans) + mul) % BigInt(mod));
        s += t;
    }

    return ans;
}
