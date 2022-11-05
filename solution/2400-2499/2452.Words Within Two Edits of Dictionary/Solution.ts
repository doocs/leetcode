function twoEditWords(queries: string[], dictionary: string[]): string[] {
    const n = queries[0].length;
    return queries.filter(querie => {
        for (const s of dictionary) {
            let diff = 0;
            for (let i = 0; i < n; i++) {
                if (querie[i] !== s[i] && ++diff > 2) {
                    break;
                }
            }
            if (diff <= 2) {
                return true;
            }
        }
        return false;
    });
}
