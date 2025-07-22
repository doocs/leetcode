class Solution:
    def findCommonResponse(self, responses: List[List[str]]) -> str:
        cnt = Counter()
        for ws in responses:
            for w in set(ws):
                cnt[w] += 1
        ans = responses[0][0]
        for w, x in cnt.items():
            if cnt[ans] < x or (cnt[ans] == x and w < ans):
                ans = w
        return ans
