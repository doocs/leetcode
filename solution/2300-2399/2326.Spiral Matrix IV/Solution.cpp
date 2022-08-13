/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    vector<vector<int>> spiralMatrix(int m, int n, ListNode* head) {
        vector<vector<int>> ans(m, vector<int>(n, -1));
        int i = 0, j = 0, p = 0;
        vector<vector<int>> dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while (1) {
            ans[i][j] = head->val;
            head = head->next;
            if (!head) break;
            while (1) {
                int x = i + dirs[p][0], y = j + dirs[p][1];
                if (x < 0 || y < 0 || x >= m || y >= n || ans[x][y] >= 0)
                    p = (p + 1) % 4;
                else {
                    i = x, j = y;
                    break;
                }
            }
        }
        return ans;
    }
};