type Logger struct {
	ts map[string]int
}

func Constructor() Logger {
	return Logger{ts: make(map[string]int)}
}

func (this *Logger) ShouldPrintMessage(timestamp int, message string) bool {
	if t, ok := this.ts[message]; ok && timestamp < t {
		return false
	}
	this.ts[message] = timestamp + 10
	return true
}

/**
 * Your Logger object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.ShouldPrintMessage(timestamp,message);
 */
