/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode[] listOfDepth(TreeNode tree) {
        List<ListNode> ans = new ArrayList<>();
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(tree);
        while (!q.isEmpty()) {
            ListNode dummy = new ListNode(0);
            ListNode cur = dummy;
            for (int k = q.size(); k > 0; --k) {
                TreeNode node = q.poll();
                cur.next = new ListNode(node.val);
                cur = cur.next;
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            ans.add(dummy.next);
        }
        return ans.toArray(new ListNode[0]);
    }
}