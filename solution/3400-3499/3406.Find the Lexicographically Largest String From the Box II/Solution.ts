function answerString(word: string, numFriends: number): string {
    if (numFriends === 1) {
        return word;
    }
    const s = lastSubstring(word);
    return s.slice(0, word.length - numFriends + 1);
}

function lastSubstring(s: string): string {
    const n = s.length;
    let i = 0;
    for (let j = 1, k = 0; j + k < n; ) {
        if (s[i + k] === s[j + k]) {
            ++k;
        } else if (s[i + k] < s[j + k]) {
            i += k + 1;
            k = 0;
            if (i >= j) {
                j = i + 1;
            }
        } else {
            j += k + 1;
            k = 0;
        }
    }
    return s.slice(i);
}
