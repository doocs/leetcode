function largestMerge(word1: string, word2: string): string {
    const m = word1.length;
    const n = word2.length;
    let ans = '';
    let i = 0;
    let j = 0;
    while (i < m && j < n) {
        ans += word1.slice(i) > word2.slice(j) ? word1[i++] : word2[j++];
    }
    ans += word1.slice(i);
    ans += word2.slice(j);
    return ans;
}
