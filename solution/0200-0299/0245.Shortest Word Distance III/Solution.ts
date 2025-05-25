function shortestWordDistance(wordsDict: string[], word1: string, word2: string): number {
    let ans = wordsDict.length;
    if (word1 === word2) {
        let j = -1;
        for (let i = 0; i < wordsDict.length; i++) {
            if (wordsDict[i] === word1) {
                if (j !== -1) {
                    ans = Math.min(ans, i - j);
                }
                j = i;
            }
        }
    } else {
        let i = -1,
            j = -1;
        for (let k = 0; k < wordsDict.length; k++) {
            if (wordsDict[k] === word1) {
                i = k;
            }
            if (wordsDict[k] === word2) {
                j = k;
            }
            if (i !== -1 && j !== -1) {
                ans = Math.min(ans, Math.abs(i - j));
            }
        }
    }
    return ans;
}
