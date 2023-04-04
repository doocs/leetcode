class Trie {
    children: Trie[];
    ref: number;

    constructor() {
        this.children = new Array(26);
        this.ref = -1;
    }

    insert(w: string, ref: number): void {
        let node: Trie = this;
        for (let i = 0; i < w.length; i++) {
            const c = w.charCodeAt(i) - 97;
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
        }
        node.ref = ref;
    }
}

function findWords(board: string[][], words: string[]): string[] {
    const tree = new Trie();
    for (let i = 0; i < words.length; ++i) {
        tree.insert(words[i], i);
    }
    const m = board.length;
    const n = board[0].length;
    const ans: string[] = [];
    const dirs: number[] = [-1, 0, 1, 0, -1];
    const dfs = (node: Trie, i: number, j: number) => {
        const idx = board[i][j].charCodeAt(0) - 97;
        if (node.children[idx] == null) {
            return;
        }
        node = node.children[idx];
        if (node.ref != -1) {
            ans.push(words[node.ref]);
            node.ref = -1;
        }
        const c = board[i][j];
        board[i][j] = '#';
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '#') {
                dfs(node, x, y);
            }
        }
        board[i][j] = c;
    };
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            dfs(tree, i, j);
        }
    }
    return ans;
}
