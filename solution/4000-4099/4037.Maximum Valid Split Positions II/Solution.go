func maxValidSplits(nums []int) int {
	n := len(nums)

	pos1 := mark(nums)

	rev := make([]int, n)
	for i := 0; i < n; i++ {
		rev[i] = nums[n-1-i]
	}
	pos2 := mark(rev)

	ans := calc(nums)

	for i := 0; i < n; i++ {
		if pos1[i] || pos2[n-1-i] {
			arr := make([]int, 0, n-1)
			for j := 0; j < n; j++ {
				if i != j {
					arr = append(arr, nums[j])
				}
			}
			ans = max(ans, calc(arr))
		}
	}

	return ans
}

func mark(nums []int) []bool {
	n := len(nums)
	pos := make([]bool, n)

	pos[0] = true
	g := nums[0]

	for i := 1; i < n; i++ {
		ng := gcd(g, nums[i])
		pos[i] = ng != g
		g = ng
	}

	return pos
}

func calc(arr []int) int {
	n := len(arr)
	pre := make([]int, n)
	suf := make([]int, n)

	pre[0] = arr[0]
	for i := 1; i < n; i++ {
		pre[i] = gcd(pre[i-1], arr[i])
	}

	suf[n-1] = arr[n-1]
	for i := n - 2; i >= 0; i-- {
		suf[i] = gcd(suf[i+1], arr[i])
	}

	ans := 0
	for i := 0; i+1 < n; i++ {
		if pre[i] == suf[i+1] {
			ans++
		}
	}

	return ans
}

func gcd(a, b int) int {
	for b != 0 {
		a, b = b, a%b
	}
	return a
}
