function isItPossible(word1: string, word2: string): boolean {
    const cnt1: number[] = Array(26).fill(0);
    const cnt2: number[] = Array(26).fill(0);
    let [x, y] = [0, 0];

    for (const c of word1) {
        if (++cnt1[c.charCodeAt(0) - 'a'.charCodeAt(0)] === 1) {
            ++x;
        }
    }

    for (const c of word2) {
        if (++cnt2[c.charCodeAt(0) - 'a'.charCodeAt(0)] === 1) {
            ++y;
        }
    }

    for (let i = 0; i < 26; ++i) {
        for (let j = 0; j < 26; ++j) {
            if (cnt1[i] > 0 && cnt2[j] > 0) {
                if (i === j) {
                    if (x === y) {
                        return true;
                    }
                } else {
                    const a = x - (cnt1[i] === 1 ? 1 : 0) + (cnt1[j] === 0 ? 1 : 0);
                    const b = y - (cnt2[j] === 1 ? 1 : 0) + (cnt2[i] === 0 ? 1 : 0);
                    if (a === b) {
                        return true;
                    }
                }
            }
        }
    }

    return false;
}
