func minOperations(nums []int) (ans int) {
	count := map[int]int{}
	for _, num := range nums {
		count[num]++
	}
	for _, c := range count {
		if c < 2 {
			return -1
		}
		ans += (c + 2) / 3
	}
	return
}