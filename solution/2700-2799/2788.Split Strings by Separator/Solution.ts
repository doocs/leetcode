function splitWordsBySeparator(words: string[], separator: string): string[] {
    const ans: string[] = [];
    for (const w of words) {
        for (const s of w.split(separator)) {
            if (s.length > 0) {
                ans.push(s);
            }
        }
    }
    return ans;
}
