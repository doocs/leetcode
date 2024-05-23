function minStickers(stickers: string[], target: string): number {
    const n = target.length;
    const q: number[] = [0];
    const vis: boolean[] = Array(1 << n).fill(false);
    vis[0] = true;
    for (let ans = 0; q.length; ++ans) {
        const qq: number[] = [];
        for (const cur of q) {
            if (cur === (1 << n) - 1) {
                return ans;
            }
            for (const s of stickers) {
                const cnt: number[] = Array(26).fill(0);
                for (const c of s) {
                    cnt[c.charCodeAt(0) - 97]++;
                }
                let nxt = cur;
                for (let i = 0; i < n; ++i) {
                    const j = target.charCodeAt(i) - 97;
                    if (((cur >> i) & 1) === 0 && cnt[j]) {
                        nxt |= 1 << i;
                        cnt[j]--;
                    }
                }
                if (!vis[nxt]) {
                    vis[nxt] = true;
                    qq.push(nxt);
                }
            }
        }
        q.splice(0, q.length, ...qq);
    }
    return -1;
}
