func canArrange(arr []int, k int) bool {
	mod := make([]int, k)
	for _, v := range arr {
		mod[(v%k+k)%k]++
	}
	for i := 1; i < k; i++ {
		if mod[i] != mod[k-i] {
			return false
		}
	}
	return mod[0]%2 == 0
}