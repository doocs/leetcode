/**
 * @lc app=leetcode.cn id=95 lang=golang
 * Accepted
 * 9/9 cases passed (28 ms)
 * Your runtime beats 94 % of golang submissions
 * Your memory usage beats 100 % of golang submissions (8 MB)
 */

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

type Pair struct {
	a, b interface{}
}

func generateTrees(n int) []*TreeNode {
	if n < 1 {
		return []*TreeNode{}
	}
	cache := make(map[Pair][]*TreeNode)
	return createTrees(1, n, cache)
}

func createTrees(start, end int, cache map[Pair][]*TreeNode) []*TreeNode {
	var ret []*TreeNode
	p := Pair{start, end}
	if ret, ok := cache[p]; ok {
		return ret
	}
	if start > end {
		ret = []*TreeNode{nil}
		cache[p] = ret
		return ret
	}
	if start == end {
		node := &TreeNode{
			Val:   start,
			Left:  nil,
			Right: nil,
		}
		ret = []*TreeNode{node}
		cache[p] = ret
		return ret
	}
	ret = make([]*TreeNode, 0)
	for i := start; i <= end; i++ {
		lefts := createTrees(start, i-1, cache)
		rights := createTrees(i+1, end, cache)
		for _, left := range lefts {
			for _, right := range rights {
				node := &TreeNode{
					Val:   i,
					Left:  left,
					Right: right,
				}
				ret = append(ret, node)
			}
		}
	}
	cache[p] = ret
	return ret
}
