function reorderSpaces(text: string): string {
    let count = 0;
    for (const c of text) {
        if (c === ' ') {
            count++;
        }
    }

    const words = text.trim().split(/\s+/g);
    const n = words.length;
    if (n === 1) {
        return words.join('') + ''.padStart(count);
    }

    const rest = count % (words.length - 1);
    const per = (count - rest) / (words.length - 1);
    return words.join(''.padStart(per)) + ''.padStart(rest);
}
