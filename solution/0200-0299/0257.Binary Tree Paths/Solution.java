class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        solution(root, "", res);
        return res;
    }

    private void solution(TreeNode root, String path, List<String> res) {
        if (root != null) {
            path += root.val;
            if (root.left == null && root.right == null) {
                res.add(path);
            } else {
                path += "->";
                solution(root.left, path, res);
                solution(root.right, path, res);
            }
        }
    }
}
