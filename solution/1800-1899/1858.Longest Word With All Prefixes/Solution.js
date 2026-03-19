class Trie {
    constructor() {
        this.children = new Array(26).fill(null);
        this.isEnd = false;
    }

    insert(w) {
        let node = this;
        for (const c of w) {
            const idx = c.charCodeAt(0) - 'a'.charCodeAt(0);
            if (!node.children[idx]) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    search(w) {
        let node = this;
        for (const c of w) {
            const idx = c.charCodeAt(0) - 'a'.charCodeAt(0);
            node = node.children[idx];
            if (!node || !node.isEnd) {
                return false;
            }
        }
        return true;
    }
}

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
        if ((w.length > ans.length || (w.length === ans.length && w < ans)) && trie.search(w)) {
            ans = w;
        }
    }
    return ans;
};
