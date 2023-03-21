type DetectSquares struct {
	cnt map[int]map[int]int
}

func Constructor() DetectSquares {
	return DetectSquares{map[int]map[int]int{}}
}

func (this *DetectSquares) Add(point []int) {
	x, y := point[0], point[1]
	if _, ok := this.cnt[x]; !ok {
		this.cnt[x] = map[int]int{}
	}
	this.cnt[x][y]++
}

func (this *DetectSquares) Count(point []int) (ans int) {
	x1, y1 := point[0], point[1]
	if cnt1, ok := this.cnt[x1]; ok {
		for x2, cnt2 := range this.cnt {
			if x2 != x1 {
				d := x2 - x1
				ans += cnt2[y1] * cnt1[y1+d] * cnt2[y1+d]
				ans += cnt2[y1] * cnt1[y1-d] * cnt2[y1-d]
			}
		}
	}
	return
}

/**
 * Your DetectSquares object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Add(point);
 * param_2 := obj.Count(point);
 */