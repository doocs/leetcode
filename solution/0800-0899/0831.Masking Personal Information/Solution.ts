function maskPII(s: string): string {
    const i = s.indexOf('@');
    if (i !== -1) {
        let ans = s[0].toLowerCase() + '*****';
        for (let j = i - 1; j < s.length; ++j) {
            ans += s.charAt(j).toLowerCase();
        }
        return ans;
    }
    let t = '';
    for (const c of s) {
        if (/\d/.test(c)) {
            t += c;
        }
    }
    const cnt = t.length - 10;
    const suf = `***-***-${t.substring(t.length - 4)}`;
    return cnt === 0 ? suf : `+${'*'.repeat(cnt)}-${suf}`;
}
