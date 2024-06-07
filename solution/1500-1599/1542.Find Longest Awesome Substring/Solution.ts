function longestAwesome(s: string): number {
    const d: number[] = Array(1024).fill(-1);
    let [st, ans] = [0, 1];
    d[0] = 0;

    for (let i = 1; i <= s.length; ++i) {
        const v = s.charCodeAt(i - 1) - '0'.charCodeAt(0);
        st ^= 1 << v;

        if (d[st] >= 0) {
            ans = Math.max(ans, i - d[st]);
        } else {
            d[st] = i;
        }

        for (let v = 0; v < 10; ++v) {
            if (d[st ^ (1 << v)] >= 0) {
                ans = Math.max(ans, i - d[st ^ (1 << v)]);
            }
        }
    }

    return ans;
}
