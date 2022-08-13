class Solution {
public:
    vector<int> threeEqualParts(vector<int>& arr) {
        int n = arr.size();
        int cnt1 = accumulate(arr.begin(), arr.end(), 0);
        int cnt = cnt1 / 3;
        int mod = cnt1 % 3;
        if (mod) return {-1, -1};
        if (cnt == 0) return {0, n - 1};
        int i = find(arr, 1);
        int j = find(arr, cnt + 1);
        int k = find(arr, cnt * 2 + 1);
        while (k < n && arr[i] == arr[j] && arr[j] == arr[k]) {
            ++i;
            ++j;
            ++k;
        }
        if (k == n) return {i - 1, j};
        return {-1, -1};
    }

    int find(vector<int>& arr, int cnt) {
        int s = 0;
        for (int i = 0; i < arr.size(); ++i) {
            s += arr[i];
            if (s == cnt) return i;
        }
        return -1;
    }
};