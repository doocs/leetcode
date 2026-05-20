class Solution {
public:
    int maximumScore(vector<int>& nums, int k) {
        int maxScore = nums[k], minNum = nums[k]; // Base case.

        int leftIdx = k, rightIdx = k;

        while (0 < leftIdx || rightIdx < nums.size() - 1) {
            if (leftIdx == 0) { // Can only go right.
                rightIdx++;
                minNum = min(minNum, nums[rightIdx]);
            }

            else if (rightIdx == nums.size() - 1) { // Can only go left.
                leftIdx--;
                minNum = min(minNum, nums[leftIdx]);
            }

            else { // Can go bidirectional.
                if (nums[leftIdx - 1] >= nums[rightIdx + 1]) {
                    leftIdx--;
                    minNum = min(minNum, nums[leftIdx]);
                }

                else {
                    rightIdx++;
                    minNum = min(minNum, nums[rightIdx]);
                }
            }

            int score = minNum * (rightIdx + 1 - leftIdx);
            maxScore = max(maxScore, score);
        }

        return maxScore;
    }
};