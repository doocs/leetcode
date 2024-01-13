function repeatedCharacter(s: string): string {
    const vis = new Array(26).fill(false);
    for (const c of s) {
        const i = c.charCodeAt(0) - 'a'.charCodeAt(0);
        if (vis[i]) {
            return c;
        }
        vis[i] = true;
    }
    return ' ';
}
