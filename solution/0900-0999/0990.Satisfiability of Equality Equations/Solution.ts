class UnionFind {
    private parent: number[];

    constructor() {
        this.parent = Array.from({ length: 26 }).map((_, i) => i);
    }

    find(index: number) {
        if (this.parent[index] === index) {
            return index;
        }
        this.parent[index] = this.find(this.parent[index]);
        return this.parent[index];
    }

    union(index1: number, index2: number) {
        this.parent[this.find(index1)] = this.find(index2);
    }
}

function equationsPossible(equations: string[]): boolean {
    const uf = new UnionFind();
    for (const [a, s, _, b] of equations) {
        if (s === '=') {
            const index1 = a.charCodeAt(0) - 'a'.charCodeAt(0);
            const index2 = b.charCodeAt(0) - 'a'.charCodeAt(0);
            uf.union(index1, index2);
        }
    }
    for (const [a, s, _, b] of equations) {
        if (s === '!') {
            const index1 = a.charCodeAt(0) - 'a'.charCodeAt(0);
            const index2 = b.charCodeAt(0) - 'a'.charCodeAt(0);
            if (uf.find(index1) === uf.find(index2)) {
                return false;
            }
        }
    }
    return true;
}
