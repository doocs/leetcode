export function countPalindromicSubsequence(s) {
    const cnt = new Map();
    const n = s.length;
    let ans = 0;

    for (let i = 0; i < n; i++) {
        const ch = s[i];
        if (cnt.has(ch)) cnt.get(ch)[1] = i;
        else cnt.set(ch, [i, i]);
    }

    for (const [_, [i, j]] of cnt) {
        if (i !== j) {
            ans += new Set(s.slice(i + 1, j)).size;
        }
    }

    return ans;
}
