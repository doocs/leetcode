func readBinaryWatch(turnedOn int) []string {
	var ans []string
	for i := 0; i < 1<<10; i++ {
		h, m := i>>6, i&0b111111
		if h < 12 && m < 60 && bits.OnesCount(uint(i)) == turnedOn {
			ans = append(ans, fmt.Sprintf("%d:%02d", h, m))
		}
	}
	return ans
}