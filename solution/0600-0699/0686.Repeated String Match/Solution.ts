function repeatedStringMatch(a: string, b: string): number {
    const m: number = a.length,
        n: number = b.length;
    let ans: number = Math.ceil(n / m);
    let t: string = a.repeat(ans);

    for (let i = 0; i < 3; i++) {
        if (t.includes(b)) {
            return ans;
        }
        ans++;
        t += a;
    }

    return -1;
}
