function decodeMessage(key: string, message: string): string {
    const d = new Map<string, string>();
    for (const c of key) {
        if (c === ' ' || d.has(c)) {
            continue;
        }
        d.set(c, String.fromCharCode('a'.charCodeAt(0) + d.size));
    }
    d.set(' ', ' ');
    return [...message].map(v => d.get(v)).join('');
}
