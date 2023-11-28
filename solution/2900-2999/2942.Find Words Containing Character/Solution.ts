function findWordsContaining(words: string[], x: string): number[] {
    const ans: number[] = [];
    for (let i = 0; i < words.length; ++i) {
        if (words[i].includes(x)) {
            ans.push(i);
        }
    }
    return ans;
}
