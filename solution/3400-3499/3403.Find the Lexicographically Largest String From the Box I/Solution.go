func answerString(word string, numFriends int) (ans string) {
	if numFriends == 1 {
		return word
	}
	n := len(word)
	for i := range word {
		k := min(n-i, n-numFriends+1)
		t := word[i : i+k]
		ans = max(ans, t)
	}
	return
}
