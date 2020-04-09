class Solution:
    def circularArrayLoop(self, nums: 'List[int]') -> 'bool':
        flag = 1000
        lent = len(nums)
        drt = 1  # -1->left 1->right
        for loc in range(lent):
            if nums[loc] > 1000:
                continue
            if nums[loc] < 0:
                drt = -1
            else:
                drt = 1
            ct = (loc + nums[loc]) % lent
            flag += 1
            nums[loc] = flag
            start = flag
            tmp = ct
            while -1000 <= nums[ct] <= 1000:
                if nums[ct] * drt < 0:
                    break
                tmp = ct
                ct = (ct + nums[ct]) % lent
                flag += 1
                nums[tmp] = flag
            else:
                if nums[ct] != nums[tmp] and nums[ct] >= start:
                    return True
        return False
