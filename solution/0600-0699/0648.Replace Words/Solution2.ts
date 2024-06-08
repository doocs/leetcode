function replaceWords(dictionary: string[], sentence: string): string {
    const words = sentence.split(' ');
    const trie: Trie = {};
    const TERMINAL_MARK = 'TERMINAL_MARK';

    for (const s of dictionary) {
        let t = trie;

        for (const ch of s) {
            t[ch] ??= {};
            t = t[ch] as Trie_;
        }
        t[TERMINAL_MARK] = TERMINAL_MARK;
    }

    for (let i = 0; i < words.length; i++) {
        const s = words[i];
        let t = trie;

        for (let j = 0; j < s.length; j++) {
            const ch = s[j];

            if (!t[ch]) break;

            if ((t[ch] as Trie_)[TERMINAL_MARK]) {
                words[i] = s.slice(0, j + 1);
                break;
            }
            t = t[ch] as Trie_;
        }
    }

    return words.join(' ');
}

// prettier-ignore
type Trie = { [key: string]: Trie} | string
type Trie_ = Exclude<Trie, string>;
