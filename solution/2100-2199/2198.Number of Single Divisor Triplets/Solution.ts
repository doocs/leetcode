function singleDivisorTriplet(nums: number[]): number {
    const cnt: number[] = Array(101).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    let ans = 0;
    const f = (a: number, b: number) => (a % b === 0 ? 1 : 0);
    for (let a = 1; a <= 100; ++a) {
        for (let b = 1; b <= 100; ++b) {
            for (let c = 1; c <= 100; ++c) {
                const s = a + b + c;
                const t = f(s, a) + f(s, b) + f(s, c);
                if (t === 1) {
                    if (a === b) {
                        ans += cnt[a] * (cnt[a] - 1) * cnt[c];
                    } else if (a === c) {
                        ans += cnt[a] * (cnt[a] - 1) * cnt[b];
                    } else if (b === c) {
                        ans += cnt[b] * (cnt[b] - 1) * cnt[a];
                    } else {
                        ans += cnt[a] * cnt[b] * cnt[c];
                    }
                }
            }
        }
    }
    return ans;
}
