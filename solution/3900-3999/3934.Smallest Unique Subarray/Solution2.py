class Solution:
    def smallestUniqueSubarray(self, nums: list[int]) -> int:
        n = len(nums)
        names = [nums]  # names[k][i] same for equal windows nums[i:i + 2**k]
        for k in range(n.bit_length() - 1):  # blocks of 2, 4, 8, ... while they fit
            ids = {}  # pair of halves -> number of the whole window
            names.append(
                [
                    ids.setdefault(pair, len(ids))
                    for pair in zip(names[k], names[k][2**k :])
                ]
            )

        def found(length):  # is some window of this length unique?
            k = (
                length.bit_length() - 1
            )  # its first 2**k elements and its last 2**k cover it
            return 1 in Counter(zip(names[k], names[k][length - 2**k :])).values()

        return bisect_left(range(1, n), True, key=found) + 1
