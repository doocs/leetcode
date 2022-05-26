/**
 * Definition for a QuadTree node.
 * type Node struct {
 *     Val bool
 *     IsLeaf bool
 *     TopLeft *Node
 *     TopRight *Node
 *     BottomLeft *Node
 *     BottomRight *Node
 * }
 */

func construct(grid [][]int) *Node {
	var dfs func(a, b, c, d int) *Node
	dfs = func(a, b, c, d int) *Node {
		zero, one := 0, 0
		for i := a; i <= c; i++ {
			for j := b; j <= d; j++ {
				if grid[i][j] == 0 {
					zero = 1
				} else {
					one = 1
				}
			}
		}
		isLeaf := zero+one == 1
		val := isLeaf && one == 1
		node := &Node{Val: val, IsLeaf: isLeaf}
		if isLeaf {
			return node
		}
		node.TopLeft = dfs(a, b, (a+c)/2, (b+d)/2)
		node.TopRight = dfs(a, (b+d)/2+1, (a+c)/2, d)
		node.BottomLeft = dfs((a+c)/2+1, b, c, (b+d)/2)
		node.BottomRight = dfs((a+c)/2+1, (b+d)/2+1, c, d)
		return node
	}
	return dfs(0, 0, len(grid)-1, len(grid[0])-1)
}