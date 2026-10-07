/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func getDirections(root *TreeNode, startValue int, destValue int) string {
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

	pathToStart := []byte{}
	pathToDest := []byte{}
	dfs(root, startValue, &pathToStart)
	dfs(root, destValue, &pathToDest)
	i := 0
	for i < len(pathToStart) && i < len(pathToDest) && pathToStart[i] == pathToDest[i] {
		i++
	}
	return string(bytes.Repeat([]byte{'U'}, len(pathToStart)-i)) + string(pathToDest[i:])
}
