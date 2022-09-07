function sortSentence(s: string): string {
    const words = s.split(' ');
    const ans = new Array(words.length);
    for (const w of words) {
        const i = w.charCodeAt(w.length - 1) - '1'.charCodeAt(0);
        ans[i] = w.slice(0, w.length - 1);
    }
    return ans.join(' ');
}
