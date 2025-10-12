type ExamTracker struct {
	times []int
	pre   []int64
}

func Constructor() ExamTracker {
	return ExamTracker{[]int{0}, []int64{int64(0)}}
}

func (this *ExamTracker) Record(time int, score int) {
	this.times = append(this.times, time)
	this.pre = append(this.pre, this.pre[len(this.pre)-1]+int64(score))
}

func (this *ExamTracker) TotalScore(startTime int, endTime int) int64 {
	l := sort.SearchInts(this.times, startTime) - 1
	r := sort.SearchInts(this.times, endTime+1) - 1
	return this.pre[r] - this.pre[l]
}

/**
 * Your ExamTracker object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Record(time,score);
 * param_2 := obj.TotalScore(startTime,endTime);
 */
