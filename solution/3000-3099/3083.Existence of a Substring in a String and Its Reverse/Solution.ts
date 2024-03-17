function isSubstringPresent(s: string): boolean {
    const st: boolean[][] = Array.from({ length: 26 }, () => Array(26).fill(false));
    for (let i = 0; i < s.length - 1; ++i) {
        st[s.charCodeAt(i + 1) - 97][s.charCodeAt(i) - 97] = true;
    }
    for (let i = 0; i < s.length - 1; ++i) {
        if (st[s.charCodeAt(i) - 97][s.charCodeAt(i + 1) - 97]) {
            return true;
        }
    }
    return false;
}
