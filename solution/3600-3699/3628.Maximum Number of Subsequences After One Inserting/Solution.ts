function numOfSubsequences(s: string): number {
    const calc = (t: string): number => {
        let [cnt, a] = [0, 0];
        for (const c of s) {
            if (c === t[1]) cnt += a;
            if (c === t[0]) a++;
        }
        return cnt;
    };

    let [l, r] = [0, 0];
    for (const c of s) {
        if (c === 'T') r++;
    }

    let [ans, mx] = [0, 0];
    for (const c of s) {
        if (c === 'T') r--;
        if (c === 'C') ans += l * r;
        if (c === 'L') l++;
        mx = Math.max(mx, l * r);
    }

    mx = Math.max(mx, calc('LC'));
    mx = Math.max(mx, calc('CT'));
    ans += mx;
    return ans;
}
