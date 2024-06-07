function appendCharacters(s: string, t: string): number {
    let j = 0;
    for (const c of s) {
        if (c === t[j]) {
            ++j;
        }
    }
    return t.length - j;
}
