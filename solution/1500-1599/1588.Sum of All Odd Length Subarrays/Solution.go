func sumOddLengthSubarrays(arr []int) int {
	n := len(arr)
	presum := make([]int, n+1)
	for i := range arr {
		presum[i+1] = presum[i] + arr[i]
	}
	res := 0
	for i := 0; i < n; i++ {
		for j := 0; i+j < n; j += 2 {
			res += presum[i+j+1] - presum[i]
		}
	}
	return res
}