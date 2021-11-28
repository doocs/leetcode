class Solution:
    def findAnagrams(self, s: str, p: str) -> List[int]:
        counter = Counter(p)
        ans = []
        left = right = 0
        t = Counter()
        while right < len(s):
            t[s[right]] += 1
            while t[s[right]] > counter[s[right]]:
                t[s[left]] -= 1
                left += 1
            if right - left + 1 == len(p):
                ans.append(left)
            right += 1
        return ans
