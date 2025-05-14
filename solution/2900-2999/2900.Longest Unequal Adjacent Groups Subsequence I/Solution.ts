function getLongestSubsequence(words: string[], groups: number[]): string[] {
    const ans: string[] = [];
    for (let i = 0; i < groups.length; ++i) {
        if (i === 0 || groups[i] !== groups[i - 1]) {
            ans.push(words[i]);
        }
    }
    return ans;
}
