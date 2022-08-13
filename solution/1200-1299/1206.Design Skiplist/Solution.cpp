struct Node {
    int val;
    vector<Node*> next;
    Node(int v, int level)
        : val(v)
        , next(level, nullptr) { }
};

class Skiplist {
public:
    const int p = RAND_MAX / 4;
    const int maxLevel = 32;
    Node* head;
    int level;

    Skiplist() {
        head = new Node(-1, maxLevel);
        level = 0;
    }

    bool search(int target) {
        Node* curr = head;
        for (int i = level - 1; ~i; --i) {
            curr = findClosest(curr, i, target);
            if (curr->next[i] && curr->next[i]->val == target) return true;
        }
        return false;
    }

    void add(int num) {
        Node* curr = head;
        int lv = randomLevel();
        Node* node = new Node(num, lv);
        level = max(level, lv);
        for (int i = level - 1; ~i; --i) {
            curr = findClosest(curr, i, num);
            if (i < lv) {
                node->next[i] = curr->next[i];
                curr->next[i] = node;
            }
        }
    }

    bool erase(int num) {
        Node* curr = head;
        bool ok = false;
        for (int i = level - 1; ~i; --i) {
            curr = findClosest(curr, i, num);
            if (curr->next[i] && curr->next[i]->val == num) {
                curr->next[i] = curr->next[i]->next[i];
                ok = true;
            }
        }
        while (level > 1 && !head->next[level - 1]) --level;
        return ok;
    }

    Node* findClosest(Node* curr, int level, int target) {
        while (curr->next[level] && curr->next[level]->val < target) curr = curr->next[level];
        return curr;
    }

    int randomLevel() {
        int lv = 1;
        while (lv < maxLevel && rand() < p) ++lv;
        return lv;
    }
};

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist* obj = new Skiplist();
 * bool param_1 = obj->search(target);
 * obj->add(num);
 * bool param_3 = obj->erase(num);
 */