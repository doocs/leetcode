function answerString(word: string, numFriends: number): string {
    if (numFriends === 1) {
        return word;
    }
    const n = word.length;
    let ans = '';
    for (let i = 0; i < n; i++) {
        const t = word.slice(i, Math.min(n, i + n - (numFriends - 1)));
        ans = t > ans ? t : ans;
    }
    return ans;
}
