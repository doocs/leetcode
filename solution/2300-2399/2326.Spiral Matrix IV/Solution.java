/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] ans = new int[m][n];
        for (int[] row : ans) {
            Arrays.fill(row, -1);
        }
        int i = 0, j = 0, p = 0;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while (true) {
            ans[i][j] = head.val;
            head = head.next;
            if (head == null) {
                break;
            }
            while (true) {
                int x = i + dirs[p][0], y = j + dirs[p][1];
                if (x < 0 || y < 0 || x >= m || y >= n || ans[x][y] >= 0) {
                    p = (p + 1) % 4;
                } else {
                    i = x;
                    j = y;
                    break;
                }
            }
        }
        return ans;
    }
}