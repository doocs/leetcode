/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    // 后续遍历，遍历的同时，找最大值和计算次数
    Map<Integer, Integer> map = new HashMap<>();
    int max = Integer.MIN_VALUE;

    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        dfs(root);
        List<Integer> list = map.entrySet().stream()
                .filter(m -> m.getValue() == max).map(i -> i.getKey()).collect(Collectors.toList());
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        int sum = root.val + left + right;
        int current = map.getOrDefault(sum, 0) + 1;
        map.put(sum, current);
        max = Math.max(current, max);
        return sum;
    }
}