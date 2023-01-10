func largestWordCount(messages []string, senders []string) (ans string) {
	cnt := map[string]int{}
	for i, msg := range messages {
		v := strings.Count(msg, " ") + 1
		cnt[senders[i]] += v
	}
	for sender, v := range cnt {
		if cnt[ans] < v || (cnt[ans] == v && ans < sender) {
			ans = sender
		}
	}
	return
}