func largestSumAfterKNegations(nums []int, k int) int {
	ans := 0
	counter := make(map[int]int)
	for _, num := range nums {
		ans += num
		counter[num]++
	}
	for i := -100; i < 0; i++ {
		if counter[i] > 0 {
			ops := min(counter[i], k)
			ans -= (i * ops * 2)
			counter[i] -= ops
			counter[-i] += ops
			k -= ops
			if k == 0 {
				break
			}
		}
	}
	if k > 0 && k%2 == 1 && counter[0] == 0 {
		for i := 1; i < 101; i++ {
			if counter[i] > 0 {
				ans -= 2 * i
				break
			}
		}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}