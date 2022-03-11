func minimumOperations(nums []int) int {
	n := len(nums)
	get := func(i int) [][]int {
		freq := make(map[int]int)
		for ; i < n; i += 2 {
			freq[nums[i]]++
		}
		a, n1, b, n2 := 0, 0, 0, 0
		for k, v := range freq {
			if v > n1 {
				b, n2, a, n1 = a, n1, k, v
			} else if v > n2 {
				b, n2 = k, v
			}
		}
		return [][]int{{a, n1}, {b, n2}}
	}
	ans := 100000
	for _, e1 := range get(0) {
		for _, e2 := range get(1) {
			if e1[0] != e2[0] && ans > (n-(e1[1]+e2[1])) {
				ans = n - (e1[1] + e2[1])
			}
		}
	}
	return ans
}