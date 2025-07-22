public class LRUCache {
    private int size;
    private int capacity;
    private Dictionary<int, Node> cache = new Dictionary<int, Node>();
    private Node head = new Node();
    private Node tail = new Node();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.Next = tail;
        tail.Prev = head;
    }

    public int Get(int key) {
        if (!cache.ContainsKey(key)) {
            return -1;
        }
        Node node = cache[key];
        RemoveNode(node);
        AddToHead(node);
        return node.Val;
    }

    public void Put(int key, int value) {
        if (cache.ContainsKey(key)) {
            Node node = cache[key];
            RemoveNode(node);
            node.Val = value;
            AddToHead(node);
        } else {
            Node node = new Node(key, value);
            cache[key] = node;
            AddToHead(node);
            if (++size > capacity) {
                node = tail.Prev;
                cache.Remove(node.Key);
                RemoveNode(node);
                --size;
            }
        }
    }

    private void RemoveNode(Node node) {
        node.Prev.Next = node.Next;
        node.Next.Prev = node.Prev;
    }

    private void AddToHead(Node node) {
        node.Next = head.Next;
        node.Prev = head;
        head.Next = node;
        node.Next.Prev = node;
    }

    // Node class to represent each entry in the cache.
    private class Node {
        public int Key;
        public int Val;
        public Node Prev;
        public Node Next;

        public Node() {}

        public Node(int key, int val) {
            Key = key;
            Val = val;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.Get(key);
 * obj.Put(key,value);
 */
