class Node {
    val: number;
    next: (Node | null)[];

    constructor(val: number, level: number) {
        this.val = val;
        this.next = Array(level).fill(null);
    }
}

class Skiplist {
    private static maxLevel: number = 32;
    private static p: number = 0.25;
    private head: Node;
    private level: number;

    constructor() {
        this.head = new Node(-1, Skiplist.maxLevel);
        this.level = 0;
    }

    search(target: number): boolean {
        let curr = this.head;
        for (let i = this.level - 1; i >= 0; i--) {
            curr = this.findClosest(curr, i, target);
            if (curr.next[i] && curr.next[i]!.val === target) {
                return true;
            }
        }
        return false;
    }

    add(num: number): void {
        let curr = this.head;
        const level = this.randomLevel();
        const node = new Node(num, level);
        this.level = Math.max(this.level, level);

        for (let i = this.level - 1; i >= 0; i--) {
            curr = this.findClosest(curr, i, num);
            if (i < level) {
                node.next[i] = curr.next[i];
                curr.next[i] = node;
            }
        }
    }

    erase(num: number): boolean {
        let curr = this.head;
        let ok = false;

        for (let i = this.level - 1; i >= 0; i--) {
            curr = this.findClosest(curr, i, num);
            if (curr.next[i] && curr.next[i]!.val === num) {
                curr.next[i] = curr.next[i]!.next[i];
                ok = true;
            }
        }

        while (this.level > 1 && this.head.next[this.level - 1] === null) {
            this.level--;
        }

        return ok;
    }

    private findClosest(curr: Node, level: number, target: number): Node {
        while (curr.next[level] && curr.next[level]!.val < target) {
            curr = curr.next[level]!;
        }
        return curr;
    }

    private randomLevel(): number {
        let level = 1;
        while (level < Skiplist.maxLevel && Math.random() < Skiplist.p) {
            level++;
        }
        return level;
    }
}

/**
 * Your Skiplist object will be instantiated and called as such:
 * var obj = new Skiplist()
 * var param_1 = obj.search(target)
 * obj.add(num)
 * var param_3 = obj.erase(num)
 */
