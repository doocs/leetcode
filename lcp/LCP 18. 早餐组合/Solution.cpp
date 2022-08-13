class Solution {
public:
    int breakfastNumber(vector<int>& staple, vector<int>& drinks, int x) {
        int res = 0, n = drinks.size();
        sort(drinks.begin(), drinks.end());
        for (int s : staple) {
            int remain = x - s;
            if (remain >= drinks[0]) {
                int left = 0, right = n - 1;
                while (left < right) {
                    int mid = left + right + 1 >> 1;
                    if (drinks[mid] <= remain)
                        left = mid;
                    else
                        right = mid - 1;
                }
                res = (res + left + 1) % 1000000007;
            }
        }
        return res;
    }
};