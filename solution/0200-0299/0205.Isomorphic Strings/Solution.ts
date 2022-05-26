function isIsomorphic(s: string, t: string): boolean {
    const n = s.length;
    const help = (s: string, t: string) => {
        const map = new Map();
        for (let i = 0; i < n; i++) {
            if (map.has(s[i])) {
                if (map.get(s[i]) !== t[i]) {
                    return false;
                }
            } else {
                map.set(s[i], t[i]);
            }
        }
        return true;
    };
    return help(s, t) && help(t, s);
}
