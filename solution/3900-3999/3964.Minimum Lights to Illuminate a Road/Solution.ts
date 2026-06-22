function minLights(lights: number[]): number {
    const n = lights.length;
    const d: number[] = Array(n).fill(0);

    for (let i = 0; i < n; i++) {
        const v = lights[i];
        if (v > 0) {
            const l = Math.max(0, i - v);
            const r = Math.min(n - 1, i + v);
            d[l]++;
            if (r + 1 < n) {
                d[r + 1]--;
            }
        }
    }

    let s = 0,
        cnt = 0,
        ans = 0;
    for (const x of d) {
        s += x;
        if (s === 0) {
            cnt++;
        } else {
            ans += Math.floor((cnt + 2) / 3);
            cnt = 0;
        }
    }

    ans += Math.floor((cnt + 2) / 3);
    return ans;
}
