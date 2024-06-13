function ladderLength(beginWord: string, endWord: string, wordList: string[]): number {
    if (!wordList.includes(endWord)) return 0;

    const replace = (s: string, i: number, ch: string) => s.slice(0, i) + ch + s.slice(i + 1);
    const { length } = beginWord;
    const words: Record<string, string[]> = {};
    const g: Record<string, string[]> = {};

    for (const w of [beginWord, ...wordList]) {
        const derivatives: string[] = [];

        for (let i = 0; i < length; i++) {
            const nextW = replace(w, i, '*');
            derivatives.push(nextW);

            g[nextW] ??= [];
            g[nextW].push(w);
        }

        words[w] = derivatives;
    }

    let ans = 0;
    let q = words[beginWord];
    const vis = new Set<string>([beginWord]);

    while (q.length) {
        const nextQ: string[] = [];
        ans++;

        for (const variant of q) {
            for (const w of g[variant]) {
                if (w === endWord) return ans + 1;

                if (vis.has(w)) continue;
                vis.add(w);

                nextQ.push(...words[w]);
            }
        }

        q = nextQ;
    }

    return 0;
}
