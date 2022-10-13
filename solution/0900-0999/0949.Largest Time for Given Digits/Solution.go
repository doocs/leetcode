func largestTimeFromDigits(arr []int) string {
	ans := -1
	for i := 0; i < 4; i++ {
		for j := 0; j < 4; j++ {
			for k := 0; k < 4; k++ {
				if i != j && j != k && i != k {
					h := arr[i]*10 + arr[j]
					m := arr[k]*10 + arr[6-i-j-k]
					if h < 24 && m < 60 {
						ans = max(ans, h*60+m)
					}
				}
			}
		}
	}
	if ans < 0 {
		return ""
	}
	return fmt.Sprintf("%02d:%02d", ans/60, ans%60)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}