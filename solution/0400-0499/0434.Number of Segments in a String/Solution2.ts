function countSegments(s: string): number {
    let ans = 0;
    for (let i = 0; i < s.length; i++) {
        let c = s[i];
        if (c !== ' ' && (i === 0 || s[i - 1] === ' ')) {
            ans++;
        }
    }
    return ans;
}
