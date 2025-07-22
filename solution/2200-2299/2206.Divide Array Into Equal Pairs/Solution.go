func divideArray(nums []int) bool {
	cnt := [510]int{}
	for _, x := range nums {
		cnt[x]++
	}
	for _, v := range cnt {
		if v%2 != 0 {
			return false
		}
	}
	return true
}
