function reorderedPowerOf2(n: number): boolean {
    const f = (x: number) => {
        const cnt = Array(10).fill(0);
        while (x > 0) {
            cnt[x % 10]++;
            x = (x / 10) | 0;
        }
        return cnt.join(',');
    };
    const target = f(n);
    for (let i = 1; i <= 1_000_000_000; i <<= 1) {
        if (target === f(i)) {
            return true;
        }
    }
    return false;
}
