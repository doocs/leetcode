class Solution {
public:
    int minimumPairRemoval(vector<int>& nums) {
        vector<int> arr = nums;
        int ans = 0;

        while (!isNonDecreasing(arr)) {
            int k = 0;
            int s = arr[0] + arr[1];

            for (int i = 1; i < arr.size() - 1; ++i) {
                int t = arr[i] + arr[i + 1];
                if (s > t) {
                    s = t;
                    k = i;
                }
            }

            arr[k] = s;
            arr.erase(arr.begin() + (k + 1));
            ++ans;
        }

        return ans;
    }

private:
    bool isNonDecreasing(const vector<int>& arr) {
        for (int i = 1; i < (int) arr.size(); ++i) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
};
