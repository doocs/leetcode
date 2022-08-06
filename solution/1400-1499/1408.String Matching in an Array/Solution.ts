function stringMatching(words: string[]): string[] {
    const res: string[] = [];
    for (const target of words) {
        for (const word of words) {
            if (word !== target && word.includes(target)) {
                res.push(target);
                break;
            }
        }
    }
    return res;
}
