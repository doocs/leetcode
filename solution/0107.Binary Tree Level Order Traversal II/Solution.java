class Solution {
    List<List<Integer>> lists;
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        lists = new ArrayList<>();
        levelOrderBottom(root,0);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = lists.size() - 1; i >= 0; i--) result.add(lists.get(i));
        return result;
    }
    private void levelOrderBottom(TreeNode root, int i) {
        if (root==null) return;
        List<Integer> list;
        if (i==lists.size()){
            list = new ArrayList<>();
            lists.add(list);
        }else {
            list = lists.get(i);
        }
        list.add(root.val);
        levelOrderBottom(root.left,i+1);
        levelOrderBottom(root.right,i+1);
    }
}