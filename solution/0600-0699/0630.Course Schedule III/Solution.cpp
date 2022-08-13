class Solution {
public:
    int scheduleCourse(vector<vector<int>>& courses) {
        sort(courses.begin(), courses.end(), [](const auto& c0, const auto& c1) {
            return c0[1] < c1[1];
        });
        int s = 0;
        priority_queue<int> pq;
        for (auto& course : courses) {
            int d = course[0], e = course[1];
            pq.push(d);
            s += d;
            if (s > e) {
                s -= pq.top();
                pq.pop();
            }
        }
        return pq.size();
    }
};