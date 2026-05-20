class Solution:
    def maxChunksToSorted(self, arr: list[int]) -> int:
        prefix_maxs = []  # Max of each arr[:i + 1] where 0 <= i < len(arr).

        for num in arr:
            if not prefix_maxs:
                prefix_maxs.append(num)
                continue

            prefix_maxs.append(max(num, prefix_maxs[-1]))

        max_chunks = 1  # Base case.
        suffix_min = arr[-1]  # Min of arr[i:] where 0 <= i < len(arr).

        for idx in range(len(arr) - 1, 0, -1):
            if arr[idx] < suffix_min:
                suffix_min = arr[idx]

            if prefix_maxs[idx - 1] <= suffix_min:
                max_chunks += 1

        return max_chunks
