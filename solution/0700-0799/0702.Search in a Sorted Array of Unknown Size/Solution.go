/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * type ArrayReader struct {
 * }
 *
 * func (this *ArrayReader) get(index int) int {}
 */

func search(reader ArrayReader, target int) int {
	left, right := 0, 20000
	for left < right {
		mid := (left + right) >> 1
		if reader.get(mid) >= target {
			right = mid
		} else {
			left = mid + 1
		}
	}
	if reader.get(left) == target {
		return left
	}
	return -1
}