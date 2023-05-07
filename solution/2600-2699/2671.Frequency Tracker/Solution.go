type FrequencyTracker struct {
	cnt  map[int]int
	freq map[int]int
}

func Constructor() FrequencyTracker {
	return FrequencyTracker{map[int]int{}, map[int]int{}}
}

func (this *FrequencyTracker) Add(number int) {
	f := this.cnt[number]
	if f > 0 {
		this.freq[f]--
	}
	this.cnt[number]++
	this.freq[f+1]++
}

func (this *FrequencyTracker) DeleteOne(number int) {
	f := this.cnt[number]
	if f == 0 {
		return
	}
	this.freq[f]--
	this.cnt[number]--
	this.freq[f-1]++
}

func (this *FrequencyTracker) HasFrequency(frequency int) bool {
	return this.freq[frequency] > 0
}

/**
 * Your FrequencyTracker object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Add(number);
 * obj.DeleteOne(number);
 * param_3 := obj.HasFrequency(frequency);
 */