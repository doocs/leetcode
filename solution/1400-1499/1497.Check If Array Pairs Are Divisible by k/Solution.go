func canArrange(arr []int, k int) bool {
	cnt := make([]int, k)
	for _, x := range arr {
		cnt[(x%k+k)%k]++
	}
	for i := 1; i < k; i++ {
		if cnt[i] != cnt[k-i] {
			return false
		}
	}
	return cnt[0]%2 == 0
}