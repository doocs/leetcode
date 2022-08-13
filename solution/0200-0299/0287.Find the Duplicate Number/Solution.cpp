class Solution {
public:
    int findDuplicate(vector<int>& nums) {
        int left = 1, right = nums.size() - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            int cnt = 0;
            for (int& v : nums)
                if (v <= mid)
                    ++cnt;
            if (cnt > mid)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
};