function shortestToChar(s: string, c: string): number[] {
    const n = s.length;
    let ans = [];
    let pre = Infinity;
    for (let i = 0; i < n; i++) {
        if (s.charAt(i) == c) pre = i;
        ans[i] = Math.abs(pre - i);
    }
    pre = Infinity;
    for (let i = n - 1; i > -1; i--) {
        if (s.charAt(i) == c) pre = i;
        ans[i] = Math.min(Math.abs(pre - i), ans[i]);
    }
    return ans;
}
