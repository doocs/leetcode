class Node {
    children: (Node | null)[] = Array(26).fill(null);
    isEnd: boolean = false;
}

function minExtraChar(s: string, dictionary: string[]): number {
    const root = new Node();
    for (const w of dictionary) {
        let node = root;
        for (let k = w.length - 1; ~k; --k) {
            const i = w.charCodeAt(k) - 'a'.charCodeAt(0);
            if (node.children[i] === null) {
                node.children[i] = new Node();
            }
            node = node.children[i] as Node;
        }
        node.isEnd = true;
    }

    const n = s.length;
    const f: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        f[i] = f[i - 1] + 1;
        let node = root;
        for (let j = i - 1; ~j; --j) {
            node = (node.children[s.charCodeAt(j) - 'a'.charCodeAt(0)] as Node) || null;
            if (node === null) {
                break;
            }
            if (node.isEnd && f[j] < f[i]) {
                f[i] = f[j];
            }
        }
    }

    return f[n];
}
