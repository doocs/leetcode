class Solution:
    def findAnagrams(self, s: str, p: str) -> List[int]:
        counter = collections.Counter(p)
        res = []
        left = right = 0
        t = collections.Counter()
        while right < len(s):
            t[s[right]] += 1
            while t[s[right]] > counter[s[right]]:
                t[s[left]] -= 1
                left += 1
            if right - left == len(p) - 1:
                res.append(left)
            right += 1
        return res
