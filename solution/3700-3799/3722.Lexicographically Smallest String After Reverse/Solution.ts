function lexSmallest(s: string): string {
    let ans = s;
    const n = s.length;
    for (let k = 1; k <= n; ++k) {
        const t1 = reverse(s.slice(0, k)) + s.slice(k);
        const t2 = s.slice(0, n - k) + reverse(s.slice(n - k));
        ans = [ans, t1, t2].sort()[0];
    }
    return ans;
}

function reverse(s: string): string {
    return s.split('').reverse().join('');
}
