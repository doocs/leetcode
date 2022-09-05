class Skiplist {
    private static final int MAX_LEVEL = 32;
    private static final double P = 0.25;
    private static final Random RANDOM = new Random();
    private final Node head = new Node(-1, MAX_LEVEL);
    private int level = 0;

    public Skiplist() {
    }

    public boolean search(int target) {
        Node curr = head;
        for (int i = level - 1; i >= 0; --i) {
            curr = findClosest(curr, i, target);
            if (curr.next[i] != null && curr.next[i].val == target) {
                return true;
            }
        }
        return false;
    }

    public void add(int num) {
        Node curr = head;
        int lv = randomLevel();
        Node node = new Node(num, lv);
        level = Math.max(level, lv);
        for (int i = level - 1; i >= 0; --i) {
            curr = findClosest(curr, i, num);
            if (i < lv) {
                node.next[i] = curr.next[i];
                curr.next[i] = node;
            }
        }
    }

    public boolean erase(int num) {
        Node curr = head;
        boolean ok = false;
        for (int i = level - 1; i >= 0; --i) {
            curr = findClosest(curr, i, num);
            if (curr.next[i] != null && curr.next[i].val == num) {
                curr.next[i] = curr.next[i].next[i];
                ok = true;
            }
        }
        while (level > 1 && head.next[level - 1] == null) {
            --level;
        }
        return ok;
    }

    private Node findClosest(Node curr, int level, int target) {
        while (curr.next[level] != null && curr.next[level].val < target) {
            curr = curr.next[level];
        }
        return curr;
    }

    private static int randomLevel() {
        int level = 1;
        while (level < MAX_LEVEL && RANDOM.nextDouble() < P) {
            ++level;
        }
        return level;
    }

    static class Node {
        int val;
        Node[] next;

        Node(int val, int level) {
            this.val = val;
            next = new Node[level];
        }
    }
}

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */