func maximumProduct(nums []int, k int) int {
	h := hp{nums}
	for heap.Init(&h); k > 0; k-- {
		h.IntSlice[0]++
		heap.Fix(&h, 0)
	}
	ans := 1
	for _, x := range nums {
		ans = (ans * x) % (1e9 + 7)
	}
	return ans
}

type hp struct{ sort.IntSlice }

func (hp) Push(any)     {}
func (hp) Pop() (_ any) { return }
