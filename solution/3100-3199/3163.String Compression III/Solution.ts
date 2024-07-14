function compressedString(word: string): string {
    const ans: string[] = [];
    const n = word.length;
    for (let i = 0; i < n; ) {
        let j = i + 1;
        while (j < n && word[j] === word[i]) {
            ++j;
        }
        let k = j - i;
        while (k) {
            const x = Math.min(k, 9);
            ans.push(x + word[i]);
            k -= x;
        }
        i = j;
    }
    return ans.join('');
}
