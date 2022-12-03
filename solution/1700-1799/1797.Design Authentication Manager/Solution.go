type AuthenticationManager struct {
	t int
	d map[string]int
}

func Constructor(timeToLive int) AuthenticationManager {
	return AuthenticationManager{timeToLive, map[string]int{}}
}

func (this *AuthenticationManager) Generate(tokenId string, currentTime int) {
	this.d[tokenId] = currentTime + this.t
}

func (this *AuthenticationManager) Renew(tokenId string, currentTime int) {
	if v, ok := this.d[tokenId]; !ok || v <= currentTime {
		return
	}
	this.Generate(tokenId, currentTime)
}

func (this *AuthenticationManager) CountUnexpiredTokens(currentTime int) int {
	ans := 0
	for _, exp := range this.d {
		if exp > currentTime {
			ans++
		}
	}
	return ans
}

/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * obj := Constructor(timeToLive);
 * obj.Generate(tokenId,currentTime);
 * obj.Renew(tokenId,currentTime);
 * param_3 := obj.CountUnexpiredTokens(currentTime);
 */