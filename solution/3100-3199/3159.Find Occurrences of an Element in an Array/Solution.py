class Solution:
    def occurrencesOfElement(
        self, nums: list[int], queries: list[int], x: int
    ) -> list[int]:
        ids = [i for i, v in enumerate(nums) if v == x]
        return [ids[i - 1] if i - 1 < len(ids) else -1 for i in queries]
