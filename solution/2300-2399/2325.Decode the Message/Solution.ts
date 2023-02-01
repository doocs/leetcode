function decodeMessage(key: string, message: string): string {
    let d = new Map<string, string>();
    d.set(' ', ' ');
    const m = key.length;
    for (let i = 0, j = 0; i < m; i++) {
        const c = key.charAt(i);
        if (c != ' ' && !d.has(c)) {
            d.set(c, String.fromCharCode(97 + j++));
        }
    }
    const ans: string[] = [];
    for (const c of message) {
        ans.push(d.get(c) ?? c);
    }
    return ans.join('');
}
