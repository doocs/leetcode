class Solution {
public:
    vector<int> getLeastNumbers(vector<int>& arr, int k) {
        int n = arr.size();
        function<vector<int>(int, int)> quickSort = [&](int l, int r) -> vector<int> {
            int i = l, j = r;
            while (i < j) {
                while (i < j && arr[j] >= arr[l]) {
                    --j;
                }
                while (i < j && arr[i] <= arr[l]) {
                    ++i;
                }
                swap(arr[i], arr[j]);
            }
            swap(arr[i], arr[l]);
            if (k < i) {
                return quickSort(l, i - 1);
            }
            if (k > i) {
                return quickSort(i + 1, r);
            }
            return vector<int>(arr.begin(), arr.begin() + k);
        };
        return k == n ? arr : quickSort(0, n - 1);
    }
};