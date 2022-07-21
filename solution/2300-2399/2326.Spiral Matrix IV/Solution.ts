/**
 * Definition for singly-linked list.
 * class ListNode {
 *     val: number
 *     next: ListNode | null
 *     constructor(val?: number, next?: ListNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *     }
 * }
 */

function spiralMatrix(m: number, n: number, head: ListNode | null): number[][] {
    const dirs = [
        [0, 1],
        [1, 0],
        [0, -1],
        [-1, 0],
    ];
    let ans = Array.from({ length: m }, v => new Array(n).fill(-1));
    let i = 0,
        j = 0,
        k = 0;
    while (head) {
        ans[i][j] = head.val;
        head = head.next;
        let x = i + dirs[k][0];
        let y = j + dirs[k][1];
        if (x < 0 || x > m - 1 || y < 0 || y > n - 1 || ans[x][y] != -1) {
            k = (k + 1) % 4;
        }
        i = i + dirs[k][0];
        j = j + dirs[k][1];
    }
    return ans;
}
