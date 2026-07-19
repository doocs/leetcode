function rearrangeString(s: string, x: string, y: string): string {
    const t = s.split('');
    let i = 0;
    for (let j = 0; j < t.length; j++) {
        if (t[j] === y) {
            [t[i], t[j]] = [t[j], t[i]];
            i++;
        }
    }
    return t.join('');
}
