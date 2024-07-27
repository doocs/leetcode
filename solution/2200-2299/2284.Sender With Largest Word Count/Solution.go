func largestWordCount(messages []string, senders []string) string {
	cnt := make(map[string]int)
	for i, message := range messages {
		v := strings.Count(message, " ") + 1
		cnt[senders[i]] += v
	}

	ans := senders[0]
	for k, v := range cnt {
		if cnt[ans] < v || (cnt[ans] == v && ans < k) {
			ans = k
		}
	}
	return ans
}