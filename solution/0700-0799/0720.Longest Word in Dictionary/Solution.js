/**
 * @param {string[]} words
 * @return {string}
 */
var longestWord = function (words) {
    const trie = new Trie();
    for (const w of words) {
        trie.insert(w);
    }

    let ans = '';
    for (const w of words) {
        if (trie.search(w) && (ans.length < w.length || (ans.length === w.length && w < ans))) {
            ans = w;
        }
    }
    return ans;
};

class Trie {
    constructor() {
        this.children = Array(26).fill(null);
        this.isEnd = false;
    }

    insert(w) {
        let node = this;
        for (let i = 0; i < w.length; i++) {
            const idx = w.charCodeAt(i) - 'a'.charCodeAt(0);
            if (node.children[idx] === null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    search(w) {
        let node = this;
        for (let i = 0; i < w.length; i++) {
            const idx = w.charCodeAt(i) - 'a'.charCodeAt(0);
            if (node.children[idx] === null || !node.children[idx].isEnd) {
                return false;
            }
            node = node.children[idx];
        }
        return true;
    }
}
