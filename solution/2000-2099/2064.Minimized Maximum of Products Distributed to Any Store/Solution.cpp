class Solution {
public:
    int minimizedMaximum(int n, vector<int>& quantities) {
        int left = 1, right = 1e5;
        while (left < right) {
            int mid = (left + right) >> 1;
            int s = 0;
            for (int& q : quantities) s += (q + mid - 1) / mid;
            if (s <= n)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
};