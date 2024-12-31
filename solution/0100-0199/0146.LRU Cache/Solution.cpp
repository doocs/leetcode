class LRUCache {
private:
    struct Node {
        int key, val;
        Node* prev;
        Node* next;
        Node(int key, int val)
            : key(key)
            , val(val)
            , prev(nullptr)
            , next(nullptr) {}
    };

    int size;
    int capacity;
    Node* head;
    Node* tail;
    unordered_map<int, Node*> cache;

    void removeNode(Node* node) {
        node->prev->next = node->next;
        node->next->prev = node->prev;
    }

    void addToHead(Node* node) {
        node->next = head->next;
        node->prev = head;
        head->next->prev = node;
        head->next = node;
    }

public:
    LRUCache(int capacity)
        : size(0)
        , capacity(capacity) {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head->next = tail;
        tail->prev = head;
    }

    int get(int key) {
        if (!cache.contains(key)) {
            return -1;
        }
        Node* node = cache[key];
        removeNode(node);
        addToHead(node);
        return node->val;
    }

    void put(int key, int value) {
        if (cache.contains(key)) {
            Node* node = cache[key];
            removeNode(node);
            node->val = value;
            addToHead(node);
        } else {
            Node* node = new Node(key, value);
            cache[key] = node;
            addToHead(node);
            if (++size > capacity) {
                node = tail->prev;
                cache.erase(node->key);
                removeNode(node);
                --size;
            }
        }
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */
