function mapWordWeights(words: string[], weights: number[]): string {
    const ans: string[] = [];
    for (const w of words) {
        let s = 0;
        for (const c of w) {
            s = (s + weights[c.charCodeAt(0) - 97]) % 26;
        }
        ans.push(String.fromCharCode(97 + (25 - s)));
    }
    return ans.join('');
}
