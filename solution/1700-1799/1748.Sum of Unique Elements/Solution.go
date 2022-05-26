func sumOfUnique(nums []int) int {
	counter := make([]int, 101)
	for _, num := range nums {
		counter[num]++
	}
	ans := 0
	for i := 0; i < 101; i++ {
		if counter[i] == 1 {
			ans += i
		}
	}
	return ans
}