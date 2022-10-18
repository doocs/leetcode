class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        int[] cnt = new int[2];
        for (int v : students) {
            ++cnt[v];
        }
        int n = students.length;
        for (int i = 0; i < n; ++i) {
            if (cnt[sandwiches[i]]-- == 0) {
                return n - i;
            }
        }
        return 0;
    }
}