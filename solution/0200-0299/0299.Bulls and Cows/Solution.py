class Solution:
    def getHint(self, secret: str, guess: str) -> str:
        a_cnt = b_cnt = 0
        nums1 = dict()
        nums2 = dict()
        for i in range(len(secret)):
            if secret[i] == guess[i]:
                a_cnt += 1
            else:
                nums1[secret[i]] = nums1.get(secret[i], 0) + 1
                nums2[guess[i]] = nums2.get(guess[i], 0) + 1
        for i, v in nums1.items():
            if i in nums2:
                b_cnt += min(v, nums2[i])
        return f'{a_cnt}A{b_cnt}B'
