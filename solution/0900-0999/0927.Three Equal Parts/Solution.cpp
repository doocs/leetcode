class Solution {
public:
    vector<int> threeEqualParts(vector<int>& arr) {
        int n = arr.size();
        int cnt = accumulate(arr.begin(), arr.end(), 0);
        if (cnt % 3) return {-1, -1};
        if (!cnt) return {0, n - 1};
        cnt /= 3;

        auto find = [&](int x) {
            int s = 0;
            for (int i = 0; i < n; ++i) {
                s += arr[i];
                if (s == x) return i;
            }
            return 0;
        };
        int i = find(1), j = find(cnt + 1), k = find(cnt * 2 + 1);
        for (; k < n && arr[i] == arr[j] && arr[j] == arr[k]; ++i, ++j, ++k) {}
        return k == n ? vector<int>{i - 1, j} : vector<int>{-1, -1};
    }
};