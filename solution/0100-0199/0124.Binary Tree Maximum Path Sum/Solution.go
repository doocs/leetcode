/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

 import(
    "math"
)

func maxPathSum(root *TreeNode) int {
    maxInt := math.MinInt32
    dfs(root,&maxInt)
    return maxInt
}

func dfs(root *TreeNode,maxInt *int) int {
    if root == nil{
        return 0
    }
    
    left := max(0,dfs(root.Left,maxInt))
    right := max(0,dfs(root.Right,maxInt))
    *maxInt = max(*maxInt,root.Val + left + right)
    return max(left,right) + root.Val
}


func max(a,b int) int{
    if a > b{
        return a
    }
    return b
}