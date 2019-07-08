class Solution {
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        int n = getLength(root);
        int len = n / k, left = n % k;
        ListNode pre = null;    // 记录链尾
        for (int i = 0; i < k && root != null; ++i) {
            res[i] = root;
            int step = len;
            if (left > 0) {
                --left;
                ++step;
            }
            for (int j = 0; j < step; ++j) {
                pre = root;
                root = root.next;
            }
            pre.next = null;    // 断链
        }
        return res;
    }

    private int getLength(ListNode root) {
        int res = 0;
        while (root != null) {
            ++res;
            root = root.next;
        }
        return res;
    }
}
