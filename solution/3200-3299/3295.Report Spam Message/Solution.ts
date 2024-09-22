function reportSpam(message: string[], bannedWords: string[]): boolean {
    const s = new Set<string>(bannedWords);
    let cnt = 0;
    for (const w of message) {
        if (s.has(w) && ++cnt >= 2) {
            return true;
        }
    }
    return false;
}
