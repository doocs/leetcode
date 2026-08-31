/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> children;

    Node() {}

    Node(int _val) {
        val = _val;
    }

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Codec {
public:
    string serialize(Node* root) {
        if (!root) {
            return "";
        }
        queue<Node*> q{{root}};
        string ans = to_string(root->val);
        while (!q.empty()) {
            auto node = q.front();
            q.pop();
            for (auto child : node->children) {
                ans += "," + to_string(child->val);
                q.push(child);
            }
            ans += ",#";
        }
        return ans;
    }

    Node* deserialize(string data) {
        if (data.empty()) {
            return nullptr;
        }
        stringstream ss(data);
        string t;
        getline(ss, t, ',');
        Node* root = new Node(stoi(t), {});
        queue<Node*> q{{root}};
        while (!q.empty()) {
            auto node = q.front();
            q.pop();
            while (getline(ss, t, ',') && t != "#") {
                Node* child = new Node(stoi(t), {});
                node->children.push_back(child);
                q.push(child);
            }
        }
        return root;
    }
};

// Your Codec object will be instantiated and called as such:
// Codec codec;
// codec.deserialize(codec.serialize(root));
