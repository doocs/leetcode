/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func constructFromPrePost(preorder []int, postorder []int) *TreeNode {
	postMap := make(map[int]int)
	for index, v := range postorder {
		postMap[v] = index
	}
	var dfs func(prel, prer, postl, postr int) *TreeNode
	dfs = func(prel, prer, postl, postr int) *TreeNode {
		if prel > prer {
			return nil
		}
		root := &TreeNode{Val: preorder[prel]}
		if prel == prer {
			return root
		}
		leftRootIndex := postMap[preorder[prel+1]]
		leftLength := leftRootIndex - postl + 1
		root.Left = dfs(prel+1, prel+leftLength, postl, leftRootIndex)
		root.Right = dfs(prel+leftLength+1, prer, leftRootIndex+1, postr-1)
		return root
	}
	return dfs(0, len(preorder)-1, 0, len(postorder)-1)
}
