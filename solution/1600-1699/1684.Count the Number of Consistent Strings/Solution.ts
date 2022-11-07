function countConsistentStrings(allowed: string, words: string[]): number {
    const helper = (s: string) => {
        let res = 0;
        for (const c of s) {
            res |= 1 << (c.charCodeAt(0) - 'a'.charCodeAt(0));
        }
        return res;
    };
    const mask = helper(allowed);
    let ans = 0;
    for (const word of words) {
        if ((mask | helper(word)) === mask) {
            ans++;
        }
    }
    return ans;
}
