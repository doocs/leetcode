/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * type BinaryMatrix struct {
 *     Get func(int, int) int
 *     Dimensions func() []int
 * }
 */

func leftMostColumnWithOne(binaryMatrix BinaryMatrix) int {
	e := binaryMatrix.Dimensions()
	m, n := e[0], e[1]
	ans := n
	for i := 0; i < m; i++ {
		l, r := 0, n
		for l < r {
			mid := (l + r) >> 1
			if binaryMatrix.Get(i, mid) == 1 {
				r = mid
			} else {
				l = mid + 1
			}
		}
		ans = min(ans, l)
	}
	if ans >= n {
		return -1
	}
	return ans
}