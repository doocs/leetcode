function isPalindromic(s: string): boolean {
    const t = [...s].map(c => c.charCodeAt(0).toString(2).padStart(8, '0')).join('');
    return t === [...t].reverse().join('');
}
