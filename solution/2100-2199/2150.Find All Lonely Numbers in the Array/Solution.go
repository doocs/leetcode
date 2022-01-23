func findLonely(nums []int) []int {
	counter := make(map[int]int)
	for _, num := range nums {
		counter[num]++
	}
	var ans []int
	for k, v := range counter {
		if v == 1 && counter[k-1] == 0 && counter[k+1] == 0 {
			ans = append(ans, k)
		}
	}
	return ans
}