func countSuffix(nums []int, target []int) int {
	diff := map[int]int{}
	for _, x := range nums {
		diff[x]++
	}
	for _, x := range target {
		diff[x]--
		if diff[x] < 0 {
			return 0
		}
	}

	n := len(nums)
	maxI := n - 1
	for i, x := range nums {
		if x != target[i] {
			maxI = i
			break
		}
	}

	minI := 0
	for i := n - 1; i > 0; i-- {
		if target[i-1] < target[i] {
			minI = i
			break
		}
	}

	return max(maxI-minI+1, 0)
}
