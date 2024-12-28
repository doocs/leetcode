function minWindow(s: string, t: string): string {
    const need: number[] = Array(128).fill(0);
    const window: number[] = Array(128).fill(0);
    for (let i = 0; i < t.length; i++) {
        need[t.charCodeAt(i)]++;
    }
    const [m, n] = [s.length, t.length];
    let [k, mi, cnt] = [-1, m + 1, 0];
    for (let l = 0, r = 0; r < m; r++) {
        let c = s.charCodeAt(r);
        if (++window[c] <= need[c]) {
            cnt++;
        }
        while (cnt === n) {
            if (r - l + 1 < mi) {
                mi = r - l + 1;
                k = l;
            }

            c = s.charCodeAt(l);
            if (window[c] <= need[c]) {
                cnt--;
            }
            window[c]--;
            l++;
        }
    }
    return k < 0 ? '' : s.substring(k, k + mi);
}
