function countSubstrings(s: string): number {
    let t = "^#";
    for (const c of s) {
        t += c + "#";
    }
    t += "$";

    const n = t.length;
    const p: number[] = new Array(n).fill(0);
    let pos = 0, maxRight = 0;
    let ans = 0;

    for (let i = 1; i < n - 1; i++) {
        if (maxRight > i) {
            p[i] = Math.min(maxRight - i, p[2 * pos - i]);
        } else {
            p[i] = 1;
        }

        while (t[i - p[i]] === t[i + p[i]]) {
            p[i]++;
        }

        if (i + p[i] > maxRight) {
            maxRight = i + p[i];
            pos = i;
        }

        ans += Math.floor(p[i] / 2);
    }

    return ans;
}
