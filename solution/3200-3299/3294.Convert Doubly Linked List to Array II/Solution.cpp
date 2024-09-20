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
        while (node && node->prev) {
            node = node->prev;
        }
        vector<int> ans;
        for (; node; node = node->next) {
            ans.push_back(node->val);
        }
        return ans;
    }
};
