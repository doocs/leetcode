class Node {
public:
    int key;
    int value;
    int freq;
    Node* prev;
    Node* next;
    Node(int key, int value) {
        this->key = key;
        this->value = value;
        this->freq = 1;
        this->prev = nullptr;
        this->next = nullptr;
    }
};

class DoublyLinkedList {
public:
    Node* head;
    Node* tail;
    DoublyLinkedList() {
        this->head = new Node(-1, -1);
        this->tail = new Node(-1, -1);
        this->head->next = this->tail;
        this->tail->prev = this->head;
    }
    void addFirst(Node* node) {
        node->prev = this->head;
        node->next = this->head->next;
        this->head->next->prev = node;
        this->head->next = node;
    }
    Node* remove(Node* node) {
        node->next->prev = node->prev;
        node->prev->next = node->next;
        node->next = nullptr;
        node->prev = nullptr;
        return node;
    }
    Node* removeLast() {
        return remove(this->tail->prev);
    }
    bool isEmpty() {
        return this->head->next == this->tail;
    }
};

class LFUCache {
public:
    LFUCache(int capacity) {
        this->capacity = capacity;
        this->minFreq = 0;
    }

    int get(int key) {
        if (capacity == 0 || map.find(key) == map.end()) {
            return -1;
        }
        Node* node = map[key];
        incrFreq(node);
        return node->value;
    }

    void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (map.find(key) != map.end()) {
            Node* node = map[key];
            node->value = value;
            incrFreq(node);
            return;
        }
        if (map.size() == capacity) {
            DoublyLinkedList* list = freqMap[minFreq];
            Node* node = list->removeLast();
            map.erase(node->key);
        }
        Node* node = new Node(key, value);
        addNode(node);
        map[key] = node;
        minFreq = 1;
    }

private:
    int capacity;
    int minFreq;
    unordered_map<int, Node*> map;
    unordered_map<int, DoublyLinkedList*> freqMap;

    void incrFreq(Node* node) {
        int freq = node->freq;
        DoublyLinkedList* list = freqMap[freq];
        list->remove(node);
        if (list->isEmpty()) {
            freqMap.erase(freq);
            if (freq == minFreq) {
                minFreq++;
            }
        }
        node->freq++;
        addNode(node);
    }

    void addNode(Node* node) {
        int freq = node->freq;
        if (freqMap.find(freq) == freqMap.end()) {
            freqMap[freq] = new DoublyLinkedList();
        }
        DoublyLinkedList* list = freqMap[freq];
        list->addFirst(node);
        freqMap[freq] = list;
    }
};

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache* obj = new LFUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */