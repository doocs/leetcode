function hasSpecialSubstring(s: string, k: number): boolean {
    const n = s.length;
    for (let l = 0; l < n; ) {
        let r = l + 1;
        while (r < n && s[r] === s[l]) {
            r++;
        }
        if (r - l === k) {
            return true;
        }
        l = r;
    }
    return false;
}
