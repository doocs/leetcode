/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func getDirections(root *TreeNode, startValue int, destValue int) string {
	lca := func(root *TreeNode, p, q int) *TreeNode {
		ret := map[*TreeNode]*TreeNode{}
		stk := [][2]interface{}{{root, 0}}
		for len(stk) > 0 {
			cur := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			node, _ := cur[0].(*TreeNode)
			state := cur[1].(int)
			if state == 0 {
				if node == nil {
					continue
				}
				if node.Val == p || node.Val == q {
					ret[node] = node
					continue
				}
				stk = append(stk, [2]interface{}{node, 1}, [2]interface{}{node.Right, 0}, [2]interface{}{node.Left, 0})
			} else {
				var left, right *TreeNode
				if node.Left != nil {
					left = ret[node.Left]
				}
				if node.Right != nil {
					right = ret[node.Right]
				}
				if left != nil && right != nil {
					ret[node] = node
				} else if left != nil {
					ret[node] = left
				} else {
					ret[node] = right
				}
			}
		}
		return ret[root]
	}
	dfs := func(start *TreeNode, x int, path *[]byte) bool {
		stk := [][2]interface{}{{start, 0}}
		for len(stk) > 0 {
			cur := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			node, _ := cur[0].(*TreeNode)
			state := cur[1].(int)
			if state == 0 {
				if node == nil {
					continue
				}
				if node.Val == x {
					return true
				}
				*path = append(*path, 'L')
				stk = append(stk, [2]interface{}{node, 1}, [2]interface{}{node.Left, 0})
			} else if state == 1 {
				(*path)[len(*path)-1] = 'R'
				stk = append(stk, [2]interface{}{node, 2}, [2]interface{}{node.Right, 0})
			} else {
				*path = (*path)[:len(*path)-1]
			}
		}
		return false
	}

	node := lca(root, startValue, destValue)
	pathToStart := []byte{}
	pathToDest := []byte{}
	dfs(node, startValue, &pathToStart)
	dfs(node, destValue, &pathToDest)
	return string(bytes.Repeat([]byte{'U'}, len(pathToStart))) + string(pathToDest)
}
