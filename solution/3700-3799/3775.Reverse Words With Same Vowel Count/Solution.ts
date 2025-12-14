function reverseWords(s: string): string {
    const words = s.split(/\s+/);

    const calc = (w: string): number => {
        let cnt = 0;
        for (const c of w) {
            if ('aeiou'.includes(c)) cnt++;
        }
        return cnt;
    };

    const cnt = calc(words[0]);
    const ans: string[] = [words[0]];

    for (let i = 1; i < words.length; i++) {
        let w = words[i];
        if (calc(w) === cnt) {
            w = w.split('').reverse().join('');
        }
        ans.push(w);
    }

    return ans.join(' ');
}
