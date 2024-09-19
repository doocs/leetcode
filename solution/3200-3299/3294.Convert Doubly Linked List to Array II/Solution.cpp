/**
 * Definition for doubly-linked list.
 * class Node {
 *     int val;
 *     Node* prev;
 *     Node* next;
 *     Node() : val(0), next(nullptr), prev(nullptr) {}
 *     Node(int x) : val(x), next(nullptr), prev(nullptr) {}
 *     Node(int x, Node *prev, Node *next) : val(x), next(next), prev(prev) {}
 * };
 */
class Solution {
public:
    vector<int> toArray(Node* node) {
        Node* cur = node;
        while (cur && cur->prev) {
            cur = cur->prev;
        }
        vector<int> ans;
        while (cur) {
            ans.push_back(cur->val);
            cur = cur->next;
        }
        return ans;
    }
};
