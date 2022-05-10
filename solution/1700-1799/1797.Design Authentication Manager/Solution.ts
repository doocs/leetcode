class AuthenticationManager {
    private timeToLive: number;
    private map: Map<string, number>;

    constructor(timeToLive: number) {
        this.timeToLive = timeToLive;
        this.map = new Map<string, number>();
    }

    generate(tokenId: string, currentTime: number): void {
        this.map.set(tokenId, currentTime + this.timeToLive);
    }

    renew(tokenId: string, currentTime: number): void {
        if ((this.map.get(tokenId) ?? 0) <= currentTime) {
            return;
        }
        this.map.set(tokenId, currentTime + this.timeToLive);
    }

    countUnexpiredTokens(currentTime: number): number {
        let res = 0;
        for (const time of this.map.values()) {
            if (time > currentTime) {
                res++;
            }
        }
        return res;
    }
}

/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * var obj = new AuthenticationManager(timeToLive)
 * obj.generate(tokenId,currentTime)
 * obj.renew(tokenId,currentTime)
 * var param_3 = obj.countUnexpiredTokens(currentTime)
 */
