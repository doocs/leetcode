func minimumPairRemoval(nums []int) int {
	arr := append([]int(nil), nums...)
	ans := 0

	isNonDecreasing := func(a []int) bool {
		for i := 1; i < len(a); i++ {
			if a[i] < a[i-1] {
				return false
			}
		}
		return true
	}

	for !isNonDecreasing(arr) {
		k := 0
		s := arr[0] + arr[1]

		for i := 1; i < len(arr)-1; i++ {
			t := arr[i] + arr[i+1]
			if s > t {
				s = t
				k = i
			}
		}

		arr[k] = s
		copy(arr[k+1:], arr[k+2:])
		arr = arr[:len(arr)-1]
		ans++
	}

	return ans
}
