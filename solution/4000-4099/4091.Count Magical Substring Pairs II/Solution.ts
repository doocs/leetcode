function countMagicalPairs(s: string): number {
    const calc = (s: string): number[][] => {
        const n = s.length;
        const f = Array.from({ length: n }, () => new Array(27).fill(0));
        for (let k = 1; k <= 26; k++) {
            const cnt = new Array(26).fill(0);
            let l = 0;
            let distinct = 0;
            for (let r = 0; r < n; r++) {
                const c = s.charCodeAt(r) - 97;
                if (cnt[c]++ === 0) {
                    distinct++;
                }
                while (distinct > k) {
                    if (--cnt[s.charCodeAt(l++) - 97] === 0) {
                        distinct--;
                    }
                }
                f[r][k] = r - l + 1;
            }
        }
        return f;
    };

    const n = s.length;
    const left = calc(s);
    const right = calc(s.split('').reverse().join(''));
    let ans = 0;
    for (let j = 0; j < n - 1; j++) {
        const r = n - 2 - j;
        for (let k = 1; k <= 26; k++) {
            ans += (left[j][k] - left[j][k - 1]) * (right[r][k] - right[r][k - 1]);
        }
    }
    return ans;
}
