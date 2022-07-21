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

func intersect(quadTree1 *Node, quadTree2 *Node) *Node {
	var dfs func(*Node, *Node) *Node
	dfs = func(t1, t2 *Node) *Node {
		if t1.IsLeaf && t2.IsLeaf {
			return &Node{Val: t1.Val || t2.Val, IsLeaf: true}
		}
		if t1.IsLeaf {
			if t1.Val {
				return t1
			}
			return t2
		}
		if t2.IsLeaf {
			if t2.Val {
				return t2
			}
			return t1
		}
		res := &Node{}
		res.TopLeft = dfs(t1.TopLeft, t2.TopLeft)
		res.TopRight = dfs(t1.TopRight, t2.TopRight)
		res.BottomLeft = dfs(t1.BottomLeft, t2.BottomLeft)
		res.BottomRight = dfs(t1.BottomRight, t2.BottomRight)
		isLeaf := res.TopLeft.IsLeaf && res.TopRight.IsLeaf && res.BottomLeft.IsLeaf && res.BottomRight.IsLeaf
		sameVal := res.TopLeft.Val == res.TopRight.Val && res.TopRight.Val == res.BottomLeft.Val && res.BottomLeft.Val == res.BottomRight.Val
		if isLeaf && sameVal {
			res = res.TopLeft
		}
		return res
	}

	return dfs(quadTree1, quadTree2)
}