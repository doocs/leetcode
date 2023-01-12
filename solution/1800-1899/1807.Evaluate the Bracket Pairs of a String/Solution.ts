function evaluate(s: string, knowledge: string[][]): string {
    const n = s.length;
    const map = new Map();
    for (const [k, v] of knowledge) {
        map.set(k, v);
    }
    const ans = [];
    let i = 0;
    while (i < n) {
        if (s[i] === '(') {
            const j = s.indexOf(')', i + 1);
            ans.push(map.get(s.slice(i + 1, j)) ?? '?');
            i = j;
        } else {
            ans.push(s[i]);
        }
        i++;
    }
    return ans.join('');
}
