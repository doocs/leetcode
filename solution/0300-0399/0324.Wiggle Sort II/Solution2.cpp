class Solution {
public:
    void wiggleSort(vector<int>& nums) {
        vector<int> bucket(5001);
        for (int& v : nums) ++bucket[v];
        int n = nums.size();
        int j = 5000;
        for (int i = 1; i < n; i += 2) {
            while (bucket[j] == 0) --j;
            nums[i] = j;
            --bucket[j];
        }
        for (int i = 0; i < n; i += 2) {
            while (bucket[j] == 0) --j;
            nums[i] = j;
            --bucket[j];
        }
    }
};