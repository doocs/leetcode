class Solution {
public:
    vector<int> smallestK(vector<int>& arr, int k) {
        priority_queue<int> q;
        for (int& v : arr) {
            q.push(v);
            if (q.size() > k) {
                q.pop();
            }
        }
        vector<int> ans;
        while (q.size()) {
            ans.push_back(q.top());
            q.pop();
        }
        return ans;
    }
};