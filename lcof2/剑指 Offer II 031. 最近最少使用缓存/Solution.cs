public class LRUCache {
    class Node {
        public Node Prev;
        public Node Next;
        public int Key;
        public int Val;
    }

    private Node head = new Node();
    private Node tail = new Node();
    private Dictionary<int, Node> cache = new Dictionary<int, Node>();
    private readonly int capacity;
    private int size;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.Next = tail;
        tail.Prev = head;
    }
    
    public int Get(int key) {
        Node node;
        if (cache.TryGetValue(key, out node)) {
            moveToHead(node);
            return node.Val;
        }
        return -1;
    }
    
    public void Put(int key, int Val) {
        Node node;
        if (cache.TryGetValue(key, out node)) {
            moveToHead(node);
            node.Val = Val;
        } else {
            node = new Node() { Key = key, Val = Val };
            cache.Add(key, node);
            addToHead(node);
            if (++size > capacity) {
                node = removeTail();
                cache.Remove(node.Key);
                --size;
            }
        }
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(Node node) {
        node.Prev.Next = node.Next;
        node.Next.Prev = node.Prev;
    }

    private void addToHead(Node node) {
        node.Next = head.Next;
        node.Prev = head;
        head.Next = node;
        node.Next.Prev = node;
    }

    private Node removeTail() {
        Node node = tail.Prev;
        removeNode(node);
        return node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.Get(key);
 * obj.Put(key,Val);
 */