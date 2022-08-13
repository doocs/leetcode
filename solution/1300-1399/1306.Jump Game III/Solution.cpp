class Solution {
public:
    bool canReach(vector<int>& arr, int start) {
        int n = arr.size();
        queue<int> q {{start}};
        while (!q.empty()) {
            int i = q.front();
            if (arr[i] == 0)
                return 1;
            q.pop();
            for (int j : {i + arr[i], i - arr[i]}) {
                if (j >= 0 && j < n && arr[j] >= 0)
                    q.push(j);
            }
            arr[i] = -1;
        }
        return 0;
    }
};