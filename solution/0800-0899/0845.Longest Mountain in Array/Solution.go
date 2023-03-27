func longestMountain(arr []int) (ans int) {
	n := len(arr)
	for l, r := 0, 0; l+2 < n; l = r {
		r = l + 1
		if arr[l] < arr[r] {
			for r+1 < n && arr[r] < arr[r+1] {
				r++
			}
			if r+1 < n && arr[r] > arr[r+1] {
				for r+1 < n && arr[r] > arr[r+1] {
					r++
				}
				ans = max(ans, r-l+1)
			} else {
				r++
			}
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}