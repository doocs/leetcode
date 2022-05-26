class TrieNode {
    children;
    isEnd;
    constructor() {
        this.children = new Array(26);
        this.isEnd = false;
    }
}

class Trie {
    root;
    constructor() {
        this.root = new TrieNode();
    }

    insert(word: string): void {
        let head = this.root;
        for (let char of word) {
            let index = char.charCodeAt(0) - 97;
            if (!head.children[index]) {
                head.children[index] = new TrieNode();
            }
            head = head.children[index];
        }
        head.isEnd = true;
    }

    search(word: string): boolean {
        let head = this.searchPrefix(word);
        return head != null && head.isEnd;
    }

    startsWith(prefix: string): boolean {
        return this.searchPrefix(prefix) != null;
    }

    private searchPrefix(prefix: string) {
        let head = this.root;
        for (let char of prefix) {
            let index = char.charCodeAt(0) - 97;
            if (!head.children[index]) return null;
            head = head.children[index];
        }
        return head;
    }
}
