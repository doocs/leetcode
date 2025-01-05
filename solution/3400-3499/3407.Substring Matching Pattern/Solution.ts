function hasMatch(s: string, p: string): boolean {
    let i = 0;
    for (const t of p.split('*')) {
        const j = s.indexOf(t, i);
        if (j === -1) {
            return false;
        }
        i = j + t.length;
    }
    return true;
}
