class LinkNode {
    public val: number;
    public next: LinkNode;

    constructor(val: number = 0, next?: LinkNode) {
        this.val = val;
        this.next = next;
    }
}

function findTheWinner(n: number, k: number): number {
    if (k === 1) {
        return n;
    }
    const dummy = new LinkNode(0);
    let cur = dummy;
    for (let i = 1; i <= n; i++) {
        cur.next = new LinkNode(i);
        cur = cur.next;
    }
    cur.next = dummy.next;

    cur = dummy;
    let count = 0;
    while (cur.next != cur) {
        count++;
        if (count === k) {
            cur.next = cur.next.next;
            count = 0;
        } else {
            cur = cur.next;
        }
    }
    return cur.val;
}
