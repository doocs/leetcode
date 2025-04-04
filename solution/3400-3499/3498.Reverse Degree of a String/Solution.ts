function reverseDegree(s: string): number {
    let ans = 0;
    for (let i = 1; i <= s.length; ++i) {
        const x = 26 - (s.charCodeAt(i - 1) - 'a'.charCodeAt(0));
        ans += i * x;
    }
    return ans;
}
