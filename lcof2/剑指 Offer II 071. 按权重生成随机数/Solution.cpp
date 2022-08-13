class Solution {
public:
    vector<int> presum;

    Solution(vector<int>& w) {
        int n = w.size();
        presum.resize(n + 1);
        for (int i = 0; i < n; ++i) presum[i + 1] = presum[i] + w[i];
    }

    int pickIndex() {
        int n = presum.size();
        int x = rand() % presum[n - 1] + 1;
        int left = 0, right = n - 2;
        while (left < right) {
            int mid = left + right >> 1;
            if (presum[mid + 1] >= x)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(w);
 * int param_1 = obj->pickIndex();
 */