class LockingTree:
    def __init__(self, parent: List[int]):
        self.nums = {}
        self.parent = parent
        self.children = defaultdict(list)
        for i, p in enumerate(parent):
            self.children[p].append(i)

    def lock(self, num: int, user: int) -> bool:
        if num in self.nums:
            return False
        self.nums[num] = user
        return True

    def unlock(self, num: int, user: int) -> bool:
        if num not in self.nums or self.nums[num] != user:
            return False
        self.nums.pop(num)
        return True

    def upgrade(self, num: int, user: int) -> bool:
        def dfs(num):
            nonlocal find
            for child in self.children[num]:
                if child in self.nums:
                    self.nums.pop(child)
                    find = True
                dfs(child)

        t = num
        while t != -1:
            if t in self.nums:
                return False
            t = self.parent[t]
        find = False
        dfs(num)
        if not find:
            return False
        self.nums[num] = user
        return True


# Your LockingTree object will be instantiated and called as such:
# obj = LockingTree(parent)
# param_1 = obj.lock(num,user)
# param_2 = obj.unlock(num,user)
# param_3 = obj.upgrade(num,user)
