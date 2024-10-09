function minAddToMakeValid(s: string): number {
    const l = s.length;
    s = s.replace('()', '');

    return s.length === l ? l : minAddToMakeValid(s);
}
