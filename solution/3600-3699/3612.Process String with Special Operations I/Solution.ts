function processStr(s: string): string {
    const result: string[] = [];
    for (const c of s) {
        if (/[a-zA-Z]/.test(c)) {
            result.push(c);
        } else if (c === '*') {
            if (result.length > 0) {
                result.pop();
            }
        } else if (c === '#') {
            result.push(...result);
        } else if (c === '%') {
            result.reverse();
        }
    }
    return result.join('');
}
