/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * type ArrayReader struct {
 * }
 * // Compares the sum of arr[l..r] with the sum of arr[x..y]
 * // return 1 if sum(arr[l..r]) > sum(arr[x..y])
 * // return 0 if sum(arr[l..r]) == sum(arr[x..y])
 * // return -1 if sum(arr[l..r]) < sum(arr[x..y])
 * func (this *ArrayReader) compareSub(l, r, x, y int) int {}
 *
 * // Returns the length of the array
 * func (this *ArrayReader) length() int {}
 */

func getIndex(reader *ArrayReader) int {
	left, right := 0, reader.length()-1
	for left < right {
		t1, t2, t3 := left, left+(right-left)/3, left+(right-left)/3*2+1
		cmp := reader.compareSub(t1, t2, t2+1, t3)
		if cmp == 0 {
			left = t3 + 1
		} else if cmp == 1 {
			right = t2
		} else {
			left, right = t2+1, t3
		}
	}
	return left
}