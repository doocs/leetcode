func largestWordCount(messages []string, senders []string) string {
	cnt := map[string]int{}
	for i, msg := range messages {
		v := strings.Count(msg, " ") + 1
		cnt[senders[i]] += v
	}
	ans := ""
	for u, v := range cnt {
		if v > cnt[ans] || (v == cnt[ans] && u > ans) {
			ans = u
		}
	}
	return ans
}