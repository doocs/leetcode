function maxmiumScore(cards: number[], cnt: number): number {
    cards.sort((a, b) => a - b);
    let ans = 0;
    const n = cards.length;
    for (let i = 0; i < cnt; ++i) {
        ans += cards[n - i - 1];
    }
    if (ans % 2 === 0) {
        return ans;
    }
    const inf = 1 << 29;
    let mx1 = -inf,
        mx2 = -inf;
    for (let i = 0; i < n - cnt; ++i) {
        if (cards[i] % 2 === 1) {
            mx1 = cards[i];
        } else {
            mx2 = cards[i];
        }
    }
    let mi1 = inf,
        mi2 = inf;
    for (let i = n - 1; i >= n - cnt; --i) {
        if (cards[i] % 2 === 1) {
            mi2 = cards[i];
        } else {
            mi1 = cards[i];
        }
    }
    ans = Math.max(ans - mi1 + mx1, ans - mi2 + mx2);
    return ans < 0 ? 0 : ans;
}
