class Solution:

    def smallestRepunitDivByK(self, K: int) -> int:
        """
        数学题，100000这个数，错几次就试出来了，太小会报错，太大会超时
        """
        mod = 1
        for i in range(1, 100000):
            mod %= K
            if mod == 0:
                return i
            mod *= 10
            mod += 1
        return -1
