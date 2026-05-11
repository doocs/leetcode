class Solution {
public:
    long long minMaxSubarraySum(vector<int>& nums, int k) {
        long long totalMaxMinSum = 0;
        long long windowMaxSum = 0, windowMinSum = 0;

        // Format: {idx, num, shares}. Use long long to prevent overflow.
        deque<tuple<int, int, long long>> maxStack, minStack;

        for (int endIdx = 0; endIdx < nums.size(); endIdx++)
        {
            int startIdx = max(0, endIdx - k + 1);

            // Window start idx slides by 1: must update stacks' info.
            if (startIdx > 0)
            {
                get<2>(maxStack.front())--; // Decrement stack's front num shares.
                windowMaxSum -= get<1>(maxStack.front());

                // Front num out of window.
                if (get<0>(maxStack.front()) < startIdx)
                    maxStack.pop_front();

                get<2>(minStack.front())--; // Decrement stack's front num shares.
                windowMinSum -= get<1>(minStack.front());

                // Front num out of window.
                if (get<0>(minStack.front()) < startIdx)
                    minStack.pop_front();
            }

            long long num = nums[endIdx];

            long long maxShares = 1; // Base case.
            windowMaxSum += num;

            while (!maxStack.empty() && get<1>(maxStack.back()) <= num)
            {
                int prevNum = get<1>(maxStack.back());
                long long prevShares = get<2>(maxStack.back());
                maxStack.pop_back();

                maxShares += prevShares; // Max shares transition.

                // Reflect transition in max sum.
                windowMaxSum += (num - prevNum) * prevShares;
            }

            maxStack.push_back({endIdx, num, maxShares});

            long long minShares = 1; // Base case.
            windowMinSum += num;

            while (!minStack.empty() && get<1>(minStack.back()) >= num)
            {
                int prevNum = get<1>(minStack.back());
                long long prevShares = get<2>(minStack.back());
                minStack.pop_back();

                minShares += prevShares; // Min shares transition.

                // Reflect transition in min sum.
                windowMinSum += (num - prevNum) * prevShares;
            }

            minStack.push_back({endIdx, num, minShares});

            totalMaxMinSum += windowMaxSum + windowMinSum;
        }

        return totalMaxMinSum;
    }
};