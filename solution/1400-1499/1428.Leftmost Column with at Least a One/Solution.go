/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * type BinaryMatrix struct {
 *     Get func(int, int) int
 *     Dimensions func() []int
 * }
 */

func leftMostColumnWithOne(binaryMatrix BinaryMatrix) int {
	scale := binaryMatrix.Dimensions()
	rows, cols := scale[0], scale[1]
	res := -1
	for row := 0; row < rows; row++ {
		left, right := 0, cols-1
		for left < right {
			mid := (left + right) >> 1
			if binaryMatrix.Get(row, mid) == 1 {
				right = mid
			} else {
				left = mid + 1
			}
		}
		if binaryMatrix.Get(row, left) == 1 {
			if res == -1 {
				res = left
			} else {
				res = min(res, left)
			}
		}
	}
	return res
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}