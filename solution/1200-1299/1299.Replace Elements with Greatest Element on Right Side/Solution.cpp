class Solution {
public:
    vector<int> replaceElements(vector<int>& arr) {
        for (int i = arr.size() - 1, mx = -1; ~i; --i) {
            int x = arr[i];
            arr[i] = mx;
            mx = max(mx, x);
        }
        return arr;
    }
};
