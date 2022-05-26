class Solution:

    def numPairsDivisibleBy60(self, time: List[int]) -> int:
        ans = 0
        time = [i % 60 for i in time]
        # 整60s的歌曲，任取两个都满足
        count = 0
        while 0 in time:
            count += 1
            time.remove(0)
        ans += count * (count - 1) // 2

        d = {}
        for item in time:
            d[item] = d.get(item, 0) + 1
        # 整30s的歌曲，任取两个也是满足的
        ans += d.get(30, 0) * (d.get(30, 0) - 1) // 2

        # 既不是60s也不是30s的歌曲，只能找时间上互补的歌曲
        for i in range(1, 30):
            ans += d.get(i, 0) * d.get(60 - i, 0)

        return ans
