function maxActiveSectionsAfterTrade(s: string): number {
    let n = s.length;
    let [ans, mx] = [0, 0];
    let pre = Number.MIN_SAFE_INTEGER;

    for (let i = 0; i < n; ) {
        let j = i + 1;
        while (j < n && s[j] === s[i]) {
            j++;
        }
        let cur = j - i;
        if (s[i] === '1') {
            ans += cur;
        } else {
            mx = Math.max(mx, pre + cur);
            pre = cur;
        }
        i = j;
    }

    ans += mx;
    return ans;
}
