class Solution {
public:
    int maxChunksToSorted(vector<int>& arr) {
        vector<int> prefixMaxs; // Max of each arr[:i + 1]. 0 <= i < arr.size().

        for (const auto& num : arr) {
            if (prefixMaxs.empty()) {
                prefixMaxs.push_back(num);
                continue;
            }

            prefixMaxs.push_back(max(prefixMaxs.back(), num));
        }

        int maxChunks = 1; // Base case.
        int suffixMin = arr.back(); // Min of arr[i:]. 0 <= i < arr.size().

        for (int idx = arr.size() - 1; idx >= 1; idx--) {
            if (arr[idx] < suffixMin)
                suffixMin = arr[idx];

            if (prefixMaxs[idx - 1] <= suffixMin)
                maxChunks++;
        }

        return maxChunks;
    }
};