class Solution:
    def kthDistinct(self, arr: List[str], k: int) -> str:
        cnt = Counter(arr)
        for s in arr:
            if cnt[s] == 1:
                k -= 1
                if k == 0:
                    return s
        return ""
