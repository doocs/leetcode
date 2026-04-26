function sortVowels(s: string): string {
    const st = new Set('aeiou');
    const vowels: string[] = [];
    const cnt: Map<string, number> = new Map();
    for (const c of s) {
        if (!st.has(c)) {
            continue;
        }
        if (!cnt.has(c)) {
            vowels.push(c);
        }
        cnt.set(c, (cnt.get(c) || 0) + 1);
    }
    vowels.sort((a, b) => (cnt.get(b) || 0) - (cnt.get(a) || 0));
    const ans = s.split('');
    let i = 0;
    for (let k = 0; k < s.length; k++) {
        let c = s[k];
        if (!st.has(c)) {
            continue;
        }
        c = vowels[i];
        ans[k] = c;
        cnt.set(c, (cnt.get(c) || 0) - 1);
        if (cnt.get(c) === 0) {
            i++;
        }
    }
    return ans.join('');
}
