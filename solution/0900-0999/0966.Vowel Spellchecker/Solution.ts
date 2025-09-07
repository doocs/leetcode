function spellchecker(wordlist: string[], queries: string[]): string[] {
    const s = new Set(wordlist);
    const low = new Map<string, string>();
    const pat = new Map<string, string>();

    const f = (w: string): string => {
        let res = '';
        for (const c of w) {
            if ('aeiou'.includes(c)) {
                res += '*';
            } else {
                res += c;
            }
        }
        return res;
    };

    for (const w of wordlist) {
        let t = w.toLowerCase();
        if (!low.has(t)) {
            low.set(t, w);
        }
        t = f(t);
        if (!pat.has(t)) {
            pat.set(t, w);
        }
    }

    const ans: string[] = [];
    for (let q of queries) {
        if (s.has(q)) {
            ans.push(q);
            continue;
        }
        q = q.toLowerCase();
        if (low.has(q)) {
            ans.push(low.get(q)!);
            continue;
        }
        q = f(q);
        if (pat.has(q)) {
            ans.push(pat.get(q)!);
            continue;
        }
        ans.push('');
    }
    return ans;
}
