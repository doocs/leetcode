class Solution {
public:
    vector<int> maximumSumQueries(vector<int>& nums1, vector<int>& nums2, vector<vector<int>>& queries) {
        vector<int> maxValues(queries.size(), -1);

        vector<vector<int>> queriesIndices;
        for (int idx = 0; idx < queries.size(); idx++)
            queriesIndices.push_back({queries[idx][0], queries[idx][1], idx});

        // Process queries by descending x threshold and y threshold.
        // Sort ascendingly and later pop from the back.
        sort(queriesIndices.begin(), queriesIndices.end());

        vector<pair<int, int>> numsPairs; // Format: {num 1, num 2}.
        for (int idx = 0; idx < nums2.size(); idx++)
            numsPairs.push_back({nums1[idx], nums2[idx]});

        // Process queries by descending num 1 and num 2.
        // Sort by ascending num 1 and num 2 to pop from the back.
        sort(numsPairs.begin(), numsPairs.end());

        deque<pair<int, int>> stack; // Format: {num 2, sum}.

        while (!queriesIndices.empty()) {
            int queryOne = queriesIndices.back()[0];
            int queryTwo = queriesIndices.back()[1];
            int queryIdx = queriesIndices.back()[2];
            queriesIndices.pop_back();

            // Pair's num 1 >= x threshold.
            while (!numsPairs.empty() && numsPairs.back().first >= queryOne) {
                auto [numOne, numTwo] = numsPairs.back();
                numsPairs.pop_back();
                int numsSum = numOne + numTwo;

                while (!stack.empty() and stack.back().first < numTwo and stack.back().second <= numsSum)
                    stack.pop_back(); // Stack top isn't better than popped pair.

                pair<int, int> targetPair = {numTwo, numsSum};
                int insertion_idx = lower_bound(stack.begin(), stack.end(), targetPair) - stack.begin();

                if (insertion_idx == stack.size())
                    stack.insert(stack.begin() + insertion_idx, targetPair);

                else if (stack[insertion_idx].second < numsSum)
                    stack.insert(stack.begin() + insertion_idx, targetPair);
            }

            pair<int, int> queryNumTwoPair = {queryTwo, 0};

            int search_idx = lower_bound(stack.begin(), stack.end(), queryNumTwoPair) - stack.begin();
            if (search_idx < stack.size())
                maxValues[queryIdx] = stack[search_idx].second;
        }

        return maxValues;
    }
};