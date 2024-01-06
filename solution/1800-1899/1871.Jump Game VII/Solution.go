func canReach(s string, minJump int, maxJump int) bool {
	n := len(s)
	pre := make([]int, n+1)
	pre[1] = 1
	f := make([]bool, n)
	f[0] = true
	for i := 1; i < n; i++ {
		if s[i] == '0' {
			l, r := max(0, i-maxJump), i-minJump
			f[i] = l <= r && pre[r+1]-pre[l] > 0
		}
		pre[i+1] = pre[i]
		if f[i] {
			pre[i+1]++
		}
	}
	return f[n-1]
}