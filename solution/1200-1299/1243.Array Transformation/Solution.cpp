class Solution {
public:
    vector<int> transformArray(vector<int>& arr) {
        bool f = true;
        while (f) {
            f = false;
            vector<int> t = arr;
            for (int i = 1; i < arr.size() - 1; ++i) {
                if (t[i] > t[i - 1] && t[i] > t[i + 1]) {
                    --arr[i];
                    f = true;
                }
                if (t[i] < t[i - 1] && t[i] < t[i + 1]) {
                    ++arr[i];
                    f = true;
                }
            }
        }
        return arr;
    }
};