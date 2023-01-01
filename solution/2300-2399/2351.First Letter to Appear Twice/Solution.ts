function repeatedCharacter(s: string): string {
    let mask = 0;
    for (const c of s) {
        const i = c.charCodeAt(0) - 'a'.charCodeAt(0);
        if (mask & (1 << i)) {
            return c;
        }
        mask |= 1 << i;
    }
    return ' ';
}
