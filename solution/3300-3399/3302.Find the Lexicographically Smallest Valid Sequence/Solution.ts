function validSequence(word1: string, word2: string): number[] {
    const m = word1.length;
    const n = word2.length;

    const suf = new Array<number>(m + 1).fill(0);
    suf[m] = n;

    let j = n - 1;
    for (let i = m - 1; i >= 0; i--) {
        if (j >= 0 && word1[i] === word2[j]) {
            j--;
        }
        suf[i] = j + 1;
    }

    const ans: number[] = [];
    let changed = false;
    j = 0;

    for (let i = 0; i < m; i++) {
        const c = word1[i];

        if (c === word2[j] || (!changed && suf[i + 1] <= j + 1)) {
            if (c !== word2[j]) {
                changed = true;
            }

            ans.push(i);
            j++;

            if (j === n) {
                return ans;
            }
        }
    }

    return [];
}
