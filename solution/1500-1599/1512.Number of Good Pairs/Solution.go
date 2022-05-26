func numIdenticalPairs(nums []int) int {
	counter := make(map[int]int)
	for _, num := range nums {
		counter[num]++
	}
	res := 0
	for _, n := range counter {
		res += n * (n - 1)
	}
	return res >> 1
}