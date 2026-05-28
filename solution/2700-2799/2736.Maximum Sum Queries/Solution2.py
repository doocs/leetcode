class Solution:
    def maximumSumQueries(
        self, nums1: list[int], nums2: list[int], queries: list[list[int]]
    ) -> list[int]:
        max_values = [-1] * len(queries)

        queries = [(query[0], query[1], idx) for idx, query in enumerate(queries)]
        # Process queries by descending x threshold and y threshold.
        queries.sort(key=lambda x: (-x[0], -x[1]))

        tuples: list[tuple[int, int]] = []  # Format: (num 1, num 2).
        for num_1, num_2 in zip(nums1, nums2):
            tuples.append((num_1, num_2))

        # Process queries by descending num 1 and num 2.
        # Sort by ascending num 1 and num 2 to pop from the back.
        tuples.sort(key=lambda x: (x[0], x[1]))

        stack: list[tuple[int, int]] = []  # Format: (num 2, sum).

        for query_1, query_2, query_idx in queries:
            while tuples and tuples[-1][0] >= query_1:  # Tuple's num 1 >= x threshold.
                num_1, num_2 = tuples.pop(-1)
                nums_sum = num_1 + num_2

                while stack and stack[-1][0] < num_2 and stack[-1][1] <= nums_sum:
                    stack.pop(-1)  # Stack top isn't better than popped tuple.

                insertion_idx = bisect_left(stack, (num_2, nums_sum))

                if insertion_idx == len(stack):
                    stack.insert(insertion_idx, (num_2, nums_sum))

                elif stack[insertion_idx][1] < nums_sum:
                    stack.insert(insertion_idx, (num_2, nums_sum))

            search_idx = bisect_left(stack, (query_2, 0))
            if search_idx < len(stack):
                max_values[query_idx] = stack[search_idx][1]

        return max_values
