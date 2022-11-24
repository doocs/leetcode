function toLowerCase(s: string): string {
    return [...s].map(c => String.fromCharCode(c.charCodeAt(0) | 32)).join('');
}
