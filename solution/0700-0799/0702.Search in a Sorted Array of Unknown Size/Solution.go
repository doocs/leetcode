/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * type ArrayReader struct {
 * }
 *
 * func (this *ArrayReader) get(index int) int {}
 */

func search(reader ArrayReader, target int) int {
	r := 1
	for reader.get(r) < target {
		r <<= 1
	}
	l := r >> 1
	for l < r {
		mid := (l + r) >> 1
		if reader.get(mid) >= target {
			r = mid
		} else {
			l = mid + 1
		}
	}
	if reader.get(l) == target {
		return l
	}
	return -1
}