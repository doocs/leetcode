function entityParser(text: string): string {
    const d: Record<string, string> = {
        '&quot;': '"',
        '&apos;': "'",
        '&amp;': '&',
        '&gt;': '>',
        '&lt;': '<',
        '&frasl;': '/',
    };

    let ans: string = '';
    let i: number = 0;
    const n: number = text.length;

    while (i < n) {
        let found: boolean = false;
        for (let l: number = 1; l < 8; ++l) {
            const j: number = i + l;
            if (j <= n) {
                const t: string = text.substring(i, j);
                if (d.hasOwnProperty(t)) {
                    ans += d[t];
                    i = j;
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            ans += text[i++];
        }
    }

    return ans;
}
