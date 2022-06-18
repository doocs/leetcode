/**
 * Definition for node.
 * class Node {
 *     val: number
 *     next: Node | null
 *     constructor(val?: number, next?: Node) {
 *         this.val = (val===undefined ? 0 : val);
 *         this.next = (next===undefined ? null : next);
 *     }
 * }
 */

function insert(head: Node | null, insertVal: number): Node | null {
    const newNode = new Node(insertVal);
    if (head == null) {
        newNode.next = newNode;
        return newNode;
    }
    const dummy = new Node(0, head);
    let cur = dummy.next;
    while (cur.next != dummy.next) {
        const val = cur.val;
        const nextVal = cur.next.val;
        if (val > nextVal) {
            if (
                (insertVal >= val && insertVal >= nextVal) ||
                (insertVal <= val && insertVal <= nextVal)
            ) {
                break;
            }
        } else {
            if (insertVal >= val && insertVal <= nextVal) {
                break;
            }
        }
        cur = cur.next;
    }
    newNode.next = cur.next;
    cur.next = newNode;
    return dummy.next;
}
