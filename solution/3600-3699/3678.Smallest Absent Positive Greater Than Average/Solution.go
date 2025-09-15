func smallestAbsent(nums []int) int {
	s := map[int]bool{}
	sum := 0
	for _, x := range nums {
		s[x] = true
		sum += x
	}
	ans := max(1, sum/len(nums)+1)
	for s[ans] {
		ans++
	}
	return ans
}
