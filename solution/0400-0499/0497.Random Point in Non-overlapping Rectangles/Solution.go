type Solution struct {
	s     []int
	rects [][]int
}

func Constructor(rects [][]int) Solution {
	n := len(rects)
	s := make([]int, n+1)
	for i, v := range rects {
		s[i+1] = s[i] + (v[2]-v[0]+1)*(v[3]-v[1]+1)
	}
	return Solution{s, rects}
}

func (this *Solution) Pick() []int {
	n := len(this.rects)
	v := 1 + rand.Intn(this.s[len(this.s)-1])
	left, right := 0, n
	for left < right {
		mid := (left + right) >> 1
		if this.s[mid] >= v {
			right = mid
		} else {
			left = mid + 1
		}
	}
	rect := this.rects[left-1]
	x, y := rect[0]+rand.Intn(rect[2]-rect[0]+1), rect[1]+rand.Intn(rect[3]-rect[1]+1)
	return []int{x, y}
}