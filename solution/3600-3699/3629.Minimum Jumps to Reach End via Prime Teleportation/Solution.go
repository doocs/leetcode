const mx = 1000001

var factors [mx][]int

func init() {
	for i := 2; i < mx; i++ {
		if len(factors[i]) == 0 {
			for j := i; j < mx; j += i {
				factors[j] = append(factors[j], i)
			}
		}
	}
}

func minJumps(nums []int) int {
	n := len(nums)
	g := make(map[int][]int)
	for i, x := range nums {
		for _, p := range factors[x] {
			g[p] = append(g[p], i)
		}
	}
	ans := 0
	vis := make([]bool, n)
	vis[0] = true
	q := []int{0}
	for {
		nq := []int{}
		for _, i := range q {
			if i == n-1 {
				return ans
			}
			idx := append([]int{}, g[nums[i]]...)
			idx = append(idx, i+1)
			if i > 0 {
				idx = append(idx, i-1)
			}
			for _, j := range idx {
				if !vis[j] {
					vis[j] = true
					nq = append(nq, j)
				}
			}
			g[nums[i]] = []int{}
		}
		q = nq
		ans++
	}
}
