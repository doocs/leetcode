class Solution {
public:
    bool validateBinaryTreeNodes(int n, vector<int>& leftChild, vector<int>& rightChild) {
        vector<int> indeg(n, 0);
        for (int c : leftChild) {
            if (c != -1) {
                indeg[c]++;
            }
        }
        for (int c : rightChild) {
            if (c != -1) {
                indeg[c]++;
            }
        }

        int root = -1;
        for (int i = 0; i < n; i++) {
            if (indeg[i] == 0) {
                root = i;
                break;
            }
        }
        if (root == -1) {
            return false;
        }

        queue<int> q;
        unordered_set<int> vis;

        q.push(root);
        vis.insert(root);

        while (!q.empty()) {
            int i = q.front();
            q.pop();

            int j = leftChild[i];
            if (j != -1) {
                if (vis.count(j)) {
                    return false;
                }
                vis.insert(j);
                q.push(j);
            }

            j = rightChild[i];
            if (j != -1) {
                if (vis.count(j)) {
                    return false;
                }
                vis.insert(j);
                q.push(j);
            }
        }

        return vis.size() == n;
    }
};