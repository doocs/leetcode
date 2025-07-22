func answerString(word string, numFriends int) (ans string) {
	if numFriends == 1 {
		return word
	}
	n := len(word)
	for i := 0; i < n; i++ {
		t := word[i:min(n, i+n-(numFriends-1))]
		ans = max(ans, t)
	}
	return
}