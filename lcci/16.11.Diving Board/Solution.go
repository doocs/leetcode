func divingBoard(shorter int, longer int, k int) []int {
	if k == 0 {
		return []int{}
	}
	if longer == shorter {
		return []int{longer * k}
	}
	var ans []int
	for i := 0; i < k+1; i++ {
		ans = append(ans, longer*i+shorter*(k-i))
	}
	return ans
}