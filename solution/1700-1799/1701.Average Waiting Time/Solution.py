class Solution:
    def averageWaitingTime(self, customers: List[List[int]]) -> float:
        f = total_waiting_time = 0
        for arrival, time in customers:
            f = max(arrival, f) + time
            total_waiting_time += f - arrival
        return total_waiting_time / len(customers)
