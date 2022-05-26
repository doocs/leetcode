function removeAnagrams(words: string[]): string[] {
    const n = words.length;
    let ans = [];
    ans.push(words[0]);
    let pre = countWord(words[0]).join('');
    for (let i = 1; i < n; i++) {
        let cur = countWord(words[i]).join('');
        if (pre !== cur) {
            ans.push(words[i]);
            pre = cur;
        }
    }
    return ans;
}

function countWord(word: string): number[] {
    let count = new Array(128).fill(0);
    for (let i = 0; i < word.length; i++) {
        count[word.charCodeAt(i)]++;
    }
    return count;
}
