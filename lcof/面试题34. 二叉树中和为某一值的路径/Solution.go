var res [][]int 
func pathSum(root *TreeNode, sum int) [][]int {
    res = [][]int{}
    if root == nil {
        return res
    }
    helper(root, sum, []int{})
    return res
}

func helper(node *TreeNode, target int, ans []int) {
    if node == nil {
        return
    }
    ans = append(ans,node.Val)
    target -= node.Val
    if target == 0 && node.Left == nil && node.Right == nil {
        tmp := make([]int,len(ans))
        copy(tmp,ans)
        res = append(res,tmp)
    } else {
        helper(node.Left, target, ans)
        helper(node.Right, target, ans)
    }
}