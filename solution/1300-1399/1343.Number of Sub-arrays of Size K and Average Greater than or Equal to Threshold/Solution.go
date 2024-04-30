func numOfSubarrays(arr []int, k int, threshold int) (ans int) {
	threshold *= k
	s := 0
	for _, x := range arr[:k] {
		s += x
	}
	if s >= threshold {
		ans++
	}
	for i := k; i < len(arr); i++ {
		s += arr[i] - arr[i-k]
		if s >= threshold {
			ans++
		}
	}
	return
}