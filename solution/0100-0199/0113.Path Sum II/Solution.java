class Solution {
    private List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        pathSum(root,new ArrayList<>(),sum);
        return result;
    }
    private void pathSum(TreeNode root, List<Integer> list, int sum) {
        if (root==null) return;
        sum = sum - root.val;
        list.add(root.val);
        if (root.left==null && root.right==null){
            ArrayList<Integer> integers = new ArrayList<>(list);
            if (sum==0) result.add(integers);
        } else {
            pathSum(root.left,list,sum);
            pathSum(root.right,list,sum);
        }
        list.remove(list.size()-1);
    }
}