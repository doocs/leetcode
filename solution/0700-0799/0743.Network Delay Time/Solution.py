'''
There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
'''

# Performance
'''
Runtime: 496 ms, faster than 91.63% of Python3 online submissions for Network Delay Time.
Memory Usage: 14.7 MB, less than 46.15% of Python3 online submissions for Network Delay Time.
'''
from collections import deque
class Solution:
    def networkDelayTime(self, times: List[List[int]], N: int, K: int) -> int:

        # Build N+1 because index is from 1-N
        travel_times = [[] for y in range(N+1)]

        # Build the array of travel times to reduce cost of searching later
        for time in times:
            origin, dest, time_travel = time
            travel_times[origin].append((dest, time_travel))

        # Store the shortest amount of time to reach i-th node
        visited_times = [float('inf') for x in range(N+1)]
        visited_times[0] = 0
        visited_times[K] = 0


        # Store next traverse in line
        visited_queue = deque()
        visited_queue.append(K)

        # BFS
        while visited_queue:
            cur_node = visited_queue.popleft()
            for time in travel_times[cur_node]:
                (dest, time_travel) = time
                if time_travel + visited_times[cur_node] < visited_times[dest]:
                    visited_times[dest] = time_travel + visited_times[cur_node]
                    visited_queue.append(dest)

        # Only return the max if all were traversed. Return -1 otherwise
        return max(visited_times) if max(visited_times) != float('inf') else -1
