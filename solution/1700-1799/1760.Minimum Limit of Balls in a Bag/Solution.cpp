class Solution {
public:
    int minimumSize(vector<int>& nums, int maxOperations) {
        int left = 1, right = 1e9;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            long long ops = 0;
            for (int num : nums) ops += (num - 1) / mid;
            if (ops <= maxOperations)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
};