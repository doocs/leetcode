function answerString(word: string, numFriends: number): string {
    if (numFriends === 1) {
        return word;
    }
    let ans: string = '';
    const n = word.length;
    for (let i = 0; i < n; ++i) {
        const k = Math.min(n - i, n - numFriends + 1);
        const t = word.slice(i, i + k);
        if (ans < t) {
            ans = t;
        }
    }
    return ans;
}
