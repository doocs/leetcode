class Solution {
public:
    int countStudents(vector<int>& students, vector<int>& sandwiches) {
        int cnt[2] = {0};
        for (int& v : students) ++cnt[v];
        int n = students.size();
        for (int i = 0; i < n; ++i) {
            if (cnt[sandwiches[i]]-- == 0) {
                return n - i;
            }
        }
        return 0;
    }
};