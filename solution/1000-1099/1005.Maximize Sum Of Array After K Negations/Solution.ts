function largestSumAfterKNegations(nums: number[], k: number): number {
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    for (let x = -100; x < 0 && k > 0; ++x) {
        if (cnt.get(x)! > 0) {
            const m = Math.min(cnt.get(x) || 0, k);
            cnt.set(x, (cnt.get(x) || 0) - m);
            cnt.set(-x, (cnt.get(-x) || 0) + m);
            k -= m;
        }
    }
    if ((k & 1) === 1 && (cnt.get(0) || 0) === 0) {
        for (let x = 1; x <= 100; ++x) {
            if (cnt.get(x)! > 0) {
                cnt.set(x, (cnt.get(x) || 0) - 1);
                cnt.set(-x, (cnt.get(-x) || 0) + 1);
                break;
            }
        }
    }
    let ans = 0;
    for (const [key, value] of cnt.entries()) {
        ans += key * value;
    }
    return ans;
}
