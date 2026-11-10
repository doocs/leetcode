class Solution {
public:
    int countSuffix(vector<int>& nums, vector<int>& target) {
        unordered_map<int, int> diff;
        for (int x : nums) {
            diff[x]++;
        }
        for (int x : target) {
            if (--diff[x] < 0) {
                return 0;
            }
        }

        int n = nums.size();
        int max_i = n - 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] != target[i]) {
                max_i = i;
                break;
            }
        }

        int min_i = 0;
        for (int i = n - 1; i > 0; i--) {
            if (target[i - 1] < target[i]) {
                min_i = i;
                break;
            }
        }

        return max(max_i - min_i + 1, 0);
    }
};