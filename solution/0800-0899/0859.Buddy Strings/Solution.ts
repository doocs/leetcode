function buddyStrings(s: string, goal: string): boolean {
    const m = s.length;
    const n = goal.length;
    if (m != n) {
        return false;
    }
    const cnt1 = new Array(26).fill(0);
    const cnt2 = new Array(26).fill(0);
    let diff = 0;
    for (let i = 0; i < n; ++i) {
        cnt1[s.charCodeAt(i) - 'a'.charCodeAt(0)]++;
        cnt2[goal.charCodeAt(i) - 'a'.charCodeAt(0)]++;
        if (s[i] != goal[i]) {
            ++diff;
        }
    }
    for (let i = 0; i < 26; ++i) {
        if (cnt1[i] != cnt2[i]) {
            return false;
        }
    }
    return diff == 2 || (diff == 0 && cnt1.some(v => v > 1));
}
