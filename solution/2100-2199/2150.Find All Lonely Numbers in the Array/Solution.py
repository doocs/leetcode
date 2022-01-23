class Solution:
    def findLonely(self, nums: List[int]) -> List[int]:
        counter = Counter(nums)
        ans = []
        for num, cnt in counter.items():
            if cnt == 1 and counter[num - 1] == 0 and counter[num + 1] == 0:
                ans.append(num)
        return ans
