class Trie {
    children: Map<string, Trie>;
    v: number;

    constructor(v: number) {
        this.children = new Map<string, Trie>();
        this.v = v;
    }

    insert(w: string, v: number): boolean {
        let node: Trie = this;
        const ps = w.split('/').slice(1);
        for (let i = 0; i < ps.length - 1; ++i) {
            const p = ps[i];
            if (!node.children.has(p)) {
                return false;
            }
            node = node.children.get(p)!;
        }
        if (node.children.has(ps[ps.length - 1])) {
            return false;
        }
        node.children.set(ps[ps.length - 1], new Trie(v));
        return true;
    }

    search(w: string): number {
        let node: Trie = this;
        const ps = w.split('/').slice(1);
        for (const p of ps) {
            if (!node.children.has(p)) {
                return -1;
            }
            node = node.children.get(p)!;
        }
        return node.v;
    }
}

class FileSystem {
    trie: Trie;

    constructor() {
        this.trie = new Trie(-1);
    }

    createPath(path: string, value: number): boolean {
        return this.trie.insert(path, value);
    }

    get(path: string): number {
        return this.trie.search(path);
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * var obj = new FileSystem()
 * var param_1 = obj.createPath(path,value)
 * var param_2 = obj.get(path)
 */
