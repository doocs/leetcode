from sortedcontainers import SortedList


class TweetCounts:
    def __init__(self):
        self.d = {"minute": 60, "hour": 3600, "day": 86400}
        self.data = defaultdict(SortedList)

    def recordTweet(self, tweetName: str, time: int) -> None:
        self.data[tweetName].add(time)

    def getTweetCountsPerFrequency(
        self, freq: str, tweetName: str, startTime: int, endTime: int
    ) -> List[int]:
        f = self.d[freq]
        tweets = self.data[tweetName]
        t = startTime
        ans = []
        while t <= endTime:
            l = tweets.bisect_left(t)
            r = tweets.bisect_left(min(t + f, endTime + 1))
            ans.append(r - l)
            t += f
        return ans


# Your TweetCounts object will be instantiated and called as such:
# obj = TweetCounts()
# obj.recordTweet(tweetName,time)
# param_2 = obj.getTweetCountsPerFrequency(freq,tweetName,startTime,endTime)
