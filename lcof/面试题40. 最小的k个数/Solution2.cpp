class Solution {
public:
    vector<int> getLeastNumbers(vector<int>& arr, int k) {
        priority_queue<int> q;
        for (int& x : arr) {
            q.push(x);
            if (q.size() > k) {
                q.pop();
            }
        }
        vector<int> ans(k);
        for (int i = 0; i < k; ++i) {
            ans[i] = q.top();
            q.pop();
        }
        return ans;
    }
};