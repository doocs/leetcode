function modifyString(s: string): string {
    const cs = s.split('');
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        if (cs[i] === '?') {
            for (const c of 'abc') {
                if (
                    (i > 0 && cs[i - 1] === c) ||
                    (i + 1 < n && cs[i + 1] === c)
                ) {
                    continue;
                }
                cs[i] = c;
                break;
            }
        }
    }
    return cs.join('');
}
