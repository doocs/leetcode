class Solution:
    def minOperations(self, nums: List[int]) -> int:
        st = set()
        for i in range(len(nums) - 1, -1, -1):
            if nums[i] in st:
                return i // 3 + 1
            st.add(nums[i])
        return 0
