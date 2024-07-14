function twoEditWords(queries: string[], dictionary: string[]): string[] {
    const n = queries[0].length;
    return queries.filter(s => {
        for (const t of dictionary) {
            let diff = 0;
            for (let i = 0; i < n; ++i) {
                if (s[i] !== t[i]) {
                    ++diff;
                }
            }
            if (diff < 3) {
                return true;
            }
        }
        return false;
    });
}
