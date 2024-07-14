function maximumRequests(n: number, requests: number[][]): number {
    const m = requests.length;
    let ans = 0;
    const check = (mask: number): boolean => {
        const cnt = Array(n).fill(0);
        for (let i = 0; i < m; ++i) {
            if ((mask >> i) & 1) {
                const [f, t] = requests[i];
                --cnt[f];
                ++cnt[t];
            }
        }
        return cnt.every(v => v === 0);
    };
    for (let mask = 0; mask < 1 << m; ++mask) {
        const cnt = bitCount(mask);
        if (ans < cnt && check(mask)) {
            ans = cnt;
        }
    }
    return ans;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
