function minimizedStringLength(s: string): number {
    return new Set(s.split('')).size;
}
