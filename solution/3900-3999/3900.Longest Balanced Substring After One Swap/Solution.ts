function longestBalanced(s: string): number {
    const cnt0 = [...s].filter(c => c === '0').length;
    const cnt1 = s.length - cnt0;
    const pos = new Map<number, number[]>();
    pos.set(0, [-1]);
    let ans = 0;
    let pre = 0;
    for (let i = 0; i < s.length; ++i) {
        pre += s[i] === '1' ? 1 : -1;
        if (!pos.has(pre)) {
            pos.set(pre, []);
        }
        pos.get(pre)!.push(i);

        ans = Math.max(ans, i - pos.get(pre)![0]);
        if (pos.has(pre - 2)) {
            const p = pos.get(pre - 2)!;
            if ((i - p[0] - 2) >> 1 < cnt0) {
                ans = Math.max(ans, i - p[0]);
            } else if (p.length > 1) {
                ans = Math.max(ans, i - p[1]);
            }
        }

        if (pos.has(pre + 2)) {
            const p = pos.get(pre + 2)!;
            if ((i - p[0] - 2) >> 1 < cnt1) {
                ans = Math.max(ans, i - p[0]);
            } else if (p.length > 1) {
                ans = Math.max(ans, i - p[1]);
            }
        }
    }
    return ans;
}
