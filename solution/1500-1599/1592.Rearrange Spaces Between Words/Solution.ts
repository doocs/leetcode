function reorderSpaces(text: string): string {
    const spaces = (text.match(/ /g) || []).length;
    const words = text.split(/\s+/).filter(Boolean);
    if (words.length === 1) {
        return words[0] + ' '.repeat(spaces);
    }
    const cnt = Math.floor(spaces / (words.length - 1));
    const mod = spaces % (words.length - 1);
    const result = words.join(' '.repeat(cnt));
    return result + ' '.repeat(mod);
}
