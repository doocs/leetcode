func kIncreasing(arr []int, k int) int {
	searchRight := func(arr []int, x int) int {
		left, right := 0, len(arr)
		for left < right {
			mid := (left + right) >> 1
			if arr[mid] > x {
				right = mid
			} else {
				left = mid + 1
			}
		}
		return left
	}

	lis := func(arr []int) int {
		var t []int
		for _, x := range arr {
			idx := searchRight(t, x)
			if idx == len(t) {
				t = append(t, x)
			} else {
				t[idx] = x
			}
		}
		return len(arr) - len(t)
	}

	n := len(arr)
	ans := 0
	for i := 0; i < k; i++ {
		var t []int
		for j := i; j < n; j += k {
			t = append(t, arr[j])
		}
		ans += lis(t)
	}
	return ans
}