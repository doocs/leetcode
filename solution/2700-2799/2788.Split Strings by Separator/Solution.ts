function splitWordsBySeparator(words: string[], separator: string): string[] {
    return words.flatMap(w => w.split(separator).filter(s => s.length > 0));
}
