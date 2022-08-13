class Solution {
public:
    int smallestDivisor(vector<int>& nums, int threshold) {
        int left = 1, right = 1e6;
        while (left < right) {
            int mid = (left + right) >> 1;
            int s = 0;
            for (int& v : nums) s += (v + mid - 1) / mid;
            if (s <= threshold)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
};