function areAlmostEqual(s1: string, s2: string): boolean {
    let c1, c2;
    let cnt = 0;
    for (let i = 0; i < s1.length; ++i) {
        const a = s1.charAt(i);
        const b = s2.charAt(i);
        if (a != b) {
            if (++cnt > 2 || (cnt == 2 && (a != c2 || b != c1))) {
                return false;
            }
            c1 = a;
            c2 = b;
        }
    }
    return cnt != 1;
}
