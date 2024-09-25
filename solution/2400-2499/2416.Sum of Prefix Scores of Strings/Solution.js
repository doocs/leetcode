class Trie {
    constructor() {
        this.children = {};
        this.cnt = 0;
    }

    insert(w) {
        let node = this;
        for (const c of w) {
            if (!node.children[c]) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
            node.cnt++;
        }
    }

    search(w) {
        let node = this;
        let ans = 0;
        for (const c of w) {
            if (!node.children[c]) {
                return ans;
            }
            node = node.children[c];
            ans += node.cnt;
        }
        return ans;
    }
}

/**
 * @param {string[]} words
 * @return {number[]}
 */
var sumPrefixScores = function (words) {
    const trie = new Trie();
    for (const w of words) {
        trie.insert(w);
    }
    return words.map(w => trie.search(w));
};
