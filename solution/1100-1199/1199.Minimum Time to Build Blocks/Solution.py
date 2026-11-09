class Solution:
    def minBuildTime(self, blocks: list[int], split: int) -> int:
        heapify(blocks)
        while len(blocks) > 1:
            heappop(blocks)
            heappush(blocks, heappop(blocks) + split)
        return blocks[0]
