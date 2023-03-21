function divisorSubstrings(num: number, k: number): number {
    let ans = 0;
    const s = num.toString();
    for (let i = 0; i < s.length - k + 1; ++i) {
        const t = parseInt(s.substring(i, i + k));
        if (t !== 0 && num % t === 0) {
            ++ans;
        }
    }
    return ans;
}
