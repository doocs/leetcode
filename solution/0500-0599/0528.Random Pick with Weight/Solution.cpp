class Solution {
public:
    vector<int> s;

    Solution(vector<int>& w) {
        int n = w.size();
        s.resize(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + w[i];
    }

    int pickIndex() {
        int n = s.size();
        int x = 1 + rand() % s[n - 1];
        int left = 1, right = n - 1;
        while (left < right) {
            int mid = left + right >> 1;
            if (s[mid] >= x)
                right = mid;
            else
                left = mid + 1;
        }
        return left - 1;
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(w);
 * int param_1 = obj->pickIndex();
 */