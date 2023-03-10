func sumOddLengthSubarrays(arr []int) (ans int) {
	n := len(arr)
	for i := range arr {
		s := 0
		for j := i; j < n; j++ {
			s += arr[j]
			if (j-i+1)%2 == 1 {
				ans += s
			}
		}
	}
	return
}