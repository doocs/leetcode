function maximumValue(strs: string[]): number {
    const f = (s: string) => (Number.isNaN(Number(s)) ? s.length : Number(s));
    return Math.max(...strs.map(f));
}
