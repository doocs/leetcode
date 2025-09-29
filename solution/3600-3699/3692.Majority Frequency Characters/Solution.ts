function majorityFrequencyGroup(s: string): string {
    const cnt: Record<string, number> = {};
    for (const c of s) {
        cnt[c] = (cnt[c] || 0) + 1;
    }
    const f = new Map<number, string[]>();
    for (const [c, v] of Object.entries(cnt)) {
        if (!f.has(v)) {
            f.set(v, []);
        }
        f.get(v)!.push(c);
    }
    let [mx, mv] = [0, 0];
    let ans = '';
    f.forEach((cs, v) => {
        if (mx < cs.length || (mx == cs.length && mv < v)) {
            mx = cs.length;
            mv = v;
            ans = cs.join('');
        }
    });
    return ans;
}
