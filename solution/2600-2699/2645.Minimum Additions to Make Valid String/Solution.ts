function addMinimum(word: string): number {
    const s: string = 'abc';
    let ans: number = 0;
    const n: number = word.length;
    for (let i = 0, j = 0; j < n; i = (i + 1) % 3) {
        if (word[j] !== s[i]) {
            ++ans;
        } else {
            ++j;
        }
    }
    if (word[n - 1] === 'b') {
        ++ans;
    } else if (word[n - 1] === 'a') {
        ans += 2;
    }
    return ans;
}
