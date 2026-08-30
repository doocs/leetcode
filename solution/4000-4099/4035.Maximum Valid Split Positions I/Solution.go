func maxValidSplits(nums []int) int {
	n := len(nums)
	calc := func(arr []int) int {
		m := len(arr)
		pre := make([]int, m)
		suf := make([]int, m)
		pre[0] = arr[0]
		for i := 1; i < m; i++ {
			pre[i] = gcd(pre[i-1], arr[i])
		}
		suf[m-1] = arr[m-1]
		for i := m - 2; i >= 0; i-- {
			suf[i] = gcd(suf[i+1], arr[i])
		}
		ans := 0
		for i := 0; i < m-1; i++ {
			if pre[i] == suf[i+1] {
				ans++
			}
		}
		return ans
	}
	ans := 0
	for del := -1; del < n; del++ {
		arr := make([]int, 0, n)
		for i, x := range nums {
			if i != del {
				arr = append(arr, x)
			}
		}
		ans = max(ans, calc(arr))
	}
	return ans
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
