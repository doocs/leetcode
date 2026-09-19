function strStr(haystack: string, needle: string): number {
    const n = haystack.length;
    const m = needle.length;
    const mod = 2 ** 31 - 1;
    let target = 0;
    let sha = 0;
    let multi = 1;
    for (let i = 0; i < m; ++i) {
        target = (target * 256 + needle.charCodeAt(i)) % mod;
    }
    for (let i = 1; i < m; ++i) {
        multi = (multi * 256) % mod;
    }
    let left = 0;
    for (let right = 0; right < n; ++right) {
        sha = (sha * 256 + haystack.charCodeAt(right)) % mod;
        if (right - left + 1 < m) {
            continue;
        }
        if (sha === target && haystack.slice(left, right + 1) === needle) {
            return left;
        }
        sha = (sha - ((haystack.charCodeAt(left) * multi) % mod) + mod) % mod;
        ++left;
    }
    return -1;
}
