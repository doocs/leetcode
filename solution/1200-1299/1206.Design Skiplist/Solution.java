class Skiplist {

	private static final int DEFAULT_MAX_LEVEL = 16;
	private static final double DEFAULT_P_FACTOR = 0.5;

	private final Node head;
	private int currentLevel;

    public Skiplist() {
    	this.head = new Node(0, DEFAULT_MAX_LEVEL);
    	this.currentLevel = 1;
    }

    public boolean search(int target) {
    	Node node = head;
    	for (int i = currentLevel - 1; i >= 0; i--) {
    		node = findClosest(node, i, target);
    		if (node.next[i] != null && node.next[i].value == target) {
    			return true;
    		}
    	}
    	return false;
    }

    public void add(int num) {
    	int level = randomLevel();
    	currentLevel = Math.max(currentLevel, level);
    	Node newNode = new Node(num, level);
    	Node updateNode = head;
    	for (int i = currentLevel - 1; i >= 0; i--) {
    		updateNode = findClosest(updateNode, i, num);
    		if (i < level) {
    			newNode.next[i] = updateNode.next[i];
    			updateNode.next[i] = newNode;
    		}
    	}
    }

    public boolean erase(int num) {
    	boolean exist = false;
    	Node node = head;
    	for (int i = currentLevel - 1; i >= 0; i--) {
    		node = findClosest(node, i, num);
    		if (node.next[i] != null && node.next[i].value == num) {
    			node.next[i] = node.next[i].next[i];
    			exist = true;
    		}
    	}
    	while (currentLevel > 1 && head.next[currentLevel - 1] == null) {
    		currentLevel--;
    	}
    	return exist;
    }

    private Node findClosest(Node node, int level, int value) {
    	while (node.next[level] != null && node.next[level].value < value) {
    		node = node.next[level];
    	}
    	return node;
    }

    private int randomLevel() {
    	int level = 1;
    	while (level < DEFAULT_MAX_LEVEL && Math.random() < DEFAULT_P_FACTOR) {
    		level++;
    	}
    	return level;
    }

    static class Node {
    	int value;
    	Node[] next;

    	Node(int value, int level) {
    		this.value = value;
    		this.next = new Node[level];
    	}
    }
}
