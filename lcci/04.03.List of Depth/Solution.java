class Solution {
    public ListNode[] listOfDepth(TreeNode tree) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(tree);
        List<ListNode> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            int n = queue.size();
            ListNode head = new ListNode(-1);
            ListNode tail = head;
            for (int i = 0; i < n; i++) {
                TreeNode front = queue.poll();
                ListNode node = new ListNode(front.val);
                tail.next = node;
                tail = node;
                if (front.left != null) {
                    queue.offer(front.left);
                }
                if (front.right != null) {
                    queue.offer(front.right);
                }
            }
            ans.add(head.next);
        }
        return ans.toArray(new ListNode[0]);
    }
}
