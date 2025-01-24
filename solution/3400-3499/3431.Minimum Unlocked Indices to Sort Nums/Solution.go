func minUnlockedIndices(nums []int, locked []int) (ans int) {
	n := len(nums)
	first2, first3 := n, n
	last1, last2 := -1, -1
	for i, x := range nums {
		if x == 1 {
			last1 = i
		} else if x == 2 {
			if i < first2 {
				first2 = i
			}
			last2 = i
		} else {
			if i < first3 {
				first3 = i
			}
		}
	}
	if first3 < last1 {
		return -1
	}
	for i, st := range locked {
		if st == 1 && ((first2 <= i && i < last1) || (first3 <= i && i < last2)) {
			ans++
		}
	}
	return ans
}
