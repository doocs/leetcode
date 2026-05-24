class Solution {
private:
    vector<int> rightSmallerCounts;
    vector<pair<int, int>> buffer;

    void combineArrays(
        vector<pair<int, int>>& numsIndices, int leftBound, int splitIdx, int rightBound) {
        // Left side array = numsIndices[leftBound: splitIdx].
        // Right side array = numsIndices[splitIdx: rightBound + 1].
        int leftIdx = leftBound, rightIdx = splitIdx;
        int bufferIdx = leftBound;

        while (leftIdx < splitIdx && rightIdx <= rightBound) {
            if (numsIndices[leftIdx].first <= numsIndices[rightIdx].first) {
                // Iterated left side element finalizes its right smaller count.
                int leftNumIdx = numsIndices[leftIdx].second;
                rightSmallerCounts[leftNumIdx] += rightIdx - splitIdx;

                buffer[bufferIdx++] = numsIndices[leftIdx++];
            }

            else
                buffer[bufferIdx++] = numsIndices[rightIdx++];
        }

        while (leftIdx < splitIdx) {
            // Iterated left side element finalizes its right smaller count.
            int leftNumIdx = numsIndices[leftIdx].second;
            rightSmallerCounts[leftNumIdx] += rightIdx - splitIdx;

            buffer[bufferIdx++] = numsIndices[leftIdx++];
        }

        while (rightIdx <= rightBound)
            buffer[bufferIdx++] = numsIndices[rightIdx++];

        for (int idx = leftBound; idx <= rightBound; idx++)
            numsIndices[idx] = buffer[idx]; // Put buffer data back to original array.
    }

    void mergeSort(vector<pair<int, int>>& numsIndices, int leftBound, int rightBound) {
        if (leftBound == rightBound) return; // Single element.

        // Plus 1: ensure splitIdx > leftBound.
        int splitIdx = (leftBound + rightBound + 1) / 2;

        mergeSort(numsIndices, leftBound, splitIdx - 1);
        mergeSort(numsIndices, splitIdx, rightBound);

        combineArrays(numsIndices, leftBound, splitIdx, rightBound);
    }

public:
    vector<int> countSmaller(vector<int>& nums) {
        buffer.resize(nums.size()); // Against memory explosions.

        vector<pair<int, int>> numsIndices(nums.size());
        for (int idx = 0; idx < nums.size(); idx++)
            numsIndices[idx] = {nums[idx], idx};

        rightSmallerCounts.assign(nums.size(), 0);
        mergeSort(numsIndices, 0, nums.size() - 1);
        return rightSmallerCounts;
    }
};