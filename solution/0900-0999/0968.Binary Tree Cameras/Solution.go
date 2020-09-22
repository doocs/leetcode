/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

var res int
func minCameraCover(root *TreeNode) int {
    res = 0
    //三种状态，后序遍历
    if root == nil {
        return 0
    }
    if dfs(root) == 0 {
        res++
    }
    return res
}
//0:待覆盖，1：已覆盖，2：安装

func dfs(root *TreeNode) int {
    if root == nil {
        return 1
    }
    l := dfs(root.Left)
    r := dfs(root.Right)
    //左右子节点存在待覆盖状态，当前节点要安装
    if l == 0 || r == 0 {
        res++
        return 2
    } else if l == 1 && r == 1 { //左右节点均为已覆盖，则当前节点为待覆盖
        return 0
    } 
    //除上述情况外，左右子节点中至少有一个安装了监控，当前节点为已覆盖
    return 1
}
