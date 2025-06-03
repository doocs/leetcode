function shortestSuperstring(s1: string, s2: string): string {
    const m = s1.length,
        n = s2.length;

    if (m > n) {
        return shortestSuperstring(s2, s1);
    }

    if (s2.includes(s1)) {
        return s2;
    }

    for (let i = 0; i < m; i++) {
        if (s2.startsWith(s1.slice(i))) {
            return s1.slice(0, i) + s2;
        }
        if (s2.endsWith(s1.slice(0, m - i))) {
            return s2 + s1.slice(m - i);
        }
    }

    return s1 + s2;
}
