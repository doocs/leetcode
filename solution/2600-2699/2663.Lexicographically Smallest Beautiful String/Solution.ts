function smallestBeautifulString(s: string, k: number): string {
    const cs: string[] = s.split('');
    const n = cs.length;
    for (let i = n - 1; i >= 0; --i) {
        const p = cs[i].charCodeAt(0) - 97 + 1;
        for (let j = p; j < k; ++j) {
            let c = String.fromCharCode(j + 97);
            if ((i > 0 && cs[i - 1] === c) || (i > 1 && cs[i - 2] === c)) {
                continue;
            }
            cs[i] = c;
            for (let l = i + 1; l < n; ++l) {
                for (let m = 0; m < k; ++m) {
                    c = String.fromCharCode(m + 97);
                    if ((l > 0 && cs[l - 1] === c) || (l > 1 && cs[l - 2] === c)) {
                        continue;
                    }
                    cs[l] = c;
                    break;
                }
            }
            return cs.join('');
        }
    }
    return '';
}
