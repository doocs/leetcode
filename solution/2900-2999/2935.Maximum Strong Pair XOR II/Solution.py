class Trie:
    __slots__ = ("children", "cnt")

    def __init__(self):
        self.children: List[Trie | None] = [None, None]
        self.cnt = 0

    def insert(self, x: int):
        node = self
        for i in range(20, -1, -1):
            v = x >> i & 1
            if node.children[v] is None:
                node.children[v] = Trie()
            node = node.children[v]
            node.cnt += 1

    def search(self, x: int) -> int:
        node = self
        ans = 0
        for i in range(20, -1, -1):
            v = x >> i & 1
            if node.children[v ^ 1] and node.children[v ^ 1].cnt:
                ans |= 1 << i
                node = node.children[v ^ 1]
            else:
                node = node.children[v]
        return ans

    def remove(self, x: int):
        node = self
        for i in range(20, -1, -1):
            v = x >> i & 1
            node = node.children[v]
            node.cnt -= 1


class Solution:
    def maximumStrongPairXor(self, nums: List[int]) -> int:
        nums.sort()
        tree = Trie()
        ans = i = 0
        for y in nums:
            tree.insert(y)
            while y > nums[i] * 2:
                tree.remove(nums[i])
                i += 1
            ans = max(ans, tree.search(y))
        return ans
