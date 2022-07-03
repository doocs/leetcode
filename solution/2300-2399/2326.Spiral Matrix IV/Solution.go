/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func spiralMatrix(m int, n int, head *ListNode) [][]int {
	ans := make([][]int, m)
	for i := range ans {
		ans[i] = make([]int, n)
		for j := range ans[i] {
			ans[i][j] = -1
		}
	}
	i, j, p := 0, 0, 0
	dirs := [][]int{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}
	for {
		ans[i][j] = head.Val
		head = head.Next
		if head == nil {
			break
		}
		for {
			x, y := i+dirs[p][0], j+dirs[p][1]
			if x < 0 || y < 0 || x >= m || y >= n || ans[x][y] >= 0 {
				p = (p + 1) % 4
			} else {
				i, j = x, y
				break
			}
		}
	}
	return ans
}