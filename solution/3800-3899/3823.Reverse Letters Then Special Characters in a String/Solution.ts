function reverseByType(s: string): string {
    const a: string[] = [];
    const b: string[] = [];
    const t = s.split('');

    for (const c of t) {
        if (/[a-zA-Z]/.test(c)) {
            a.push(c);
        } else {
            b.push(c);
        }
    }

    let j = a.length,
        k = b.length;
    for (let i = 0; i < t.length; i++) {
        if (/[a-zA-Z]/.test(t[i])) {
            t[i] = a[--j];
        } else {
            t[i] = b[--k];
        }
    }

    return t.join('');
}
