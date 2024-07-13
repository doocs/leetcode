function licenseKeyFormatting(s: string, k: number): string {
    const n = s.length;
    let cnt = (n - (s.match(/-/g) || []).length) % k || k;
    const ans: string[] = [];
    for (let i = 0; i < n; i++) {
        const c = s[i];
        if (c === '-') {
            continue;
        }
        ans.push(c.toUpperCase());
        if (--cnt === 0) {
            cnt = k;
            if (i !== n - 1) {
                ans.push('-');
            }
        }
    }
    while (ans.at(-1) === '-') {
        ans.pop();
    }
    return ans.join('');
}
