class Solution:
    def networkDelayTime(self, times: List[List[int]], N: int, K: int) -> int:

        # Build N+1 because index is from 1-N
        travel_times = [[] for _ in range(N + 1)]

        # Build the array of travel times to reduce cost of searching later
        for time in times:
            origin, dest, time_travel = time
            travel_times[origin].append((dest, time_travel))

        # Store the shortest amount of time to reach i-th node
        visited_times = [float('inf') for x in range(N + 1)]
        visited_times[0] = 0
        visited_times[K] = 0

        # Store next traverse in line
        visited_queue = deque([K])

        # BFS
        while visited_queue:
            cur_node = visited_queue.popleft()
            for time in travel_times[cur_node]:
                dest, time_travel = time
                if time_travel + visited_times[cur_node] < visited_times[dest]:
                    visited_times[dest] = time_travel + visited_times[cur_node]
                    visited_queue.append(dest)

        # Only return the max if all were traversed. Return -1 otherwise
        return max(visited_times) if max(visited_times) != float('inf') else -1
