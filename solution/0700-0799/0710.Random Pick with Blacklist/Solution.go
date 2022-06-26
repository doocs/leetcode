type Solution struct {
	d map[int]int
	k int
}

func Constructor(n int, blacklist []int) Solution {
	k := n - len(blacklist)
	i := k
	black := map[int]bool{}
	for _, b := range blacklist {
		black[b] = true
	}
	d := map[int]int{}
	for _, b := range blacklist {
		if b < k {
			for black[i] {
				i++
			}
			d[b] = i
			i++
		}
	}
	return Solution{d, k}
}

func (this *Solution) Pick() int {
	x := rand.Intn(this.k)
	if v, ok := this.d[x]; ok {
		return v
	}
	return x
}

/**
 * Your Solution object will be instantiated and called as such:
 * obj := Constructor(n, blacklist);
 * param_1 := obj.Pick();
 */