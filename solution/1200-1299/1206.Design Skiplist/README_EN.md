# [1206. Design Skiplist](https://leetcode.com/problems/design-skiplist)

[中文文档](/solution/1200-1299/1206.Design%20Skiplist/README.md)

## Description

<p>Design a <strong>Skiplist</strong> without using any built-in libraries.</p>

<p>A <strong>skiplist</strong> is a data structure that takes <code>O(log(n))</code> time to add, erase and search. Comparing with treap and red-black tree which has the same function and performance, the code length of Skiplist can be comparatively short and the idea behind Skiplists is just simple linked lists.</p>

<p>For example, we have a Skiplist containing <code>[30,40,50,60,70,90]</code> and we want to add <code>80</code> and <code>45</code> into it. The Skiplist works this way:</p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1206.Design%20Skiplist/images/1506_skiplist.gif" style="width: 500px; height: 173px;" /><br />
<small>Artyom Kalinin [CC BY-SA 3.0], via <a href="https://commons.wikimedia.org/wiki/File:Skip_list_add_element-en.gif" target="_blank" title="Artyom Kalinin [CC BY-SA 3.0 (https://creativecommons.org/licenses/by-sa/3.0)], via Wikimedia Commons">Wikimedia Commons</a></small></p>

<p>You can see there are many layers in the Skiplist. Each layer is a sorted linked list. With the help of the top layers, add, erase and search can be faster than <code>O(n)</code>. It can be proven that the average time complexity for each operation is <code>O(log(n))</code> and space complexity is <code>O(n)</code>.</p>

<p>See more about Skiplist: <a href="https://en.wikipedia.org/wiki/Skip_list" target="_blank">https://en.wikipedia.org/wiki/Skip_list</a></p>

<p>Implement the <code>Skiplist</code> class:</p>

<ul>
	<li><code>Skiplist()</code> Initializes the object of the skiplist.</li>
	<li><code>bool search(int target)</code> Returns <code>true</code> if the integer <code>target</code> exists in the Skiplist or <code>false</code> otherwise.</li>
	<li><code>void add(int num)</code> Inserts the value <code>num</code> into the SkipList.</li>
	<li><code>bool erase(int num)</code> Removes the value <code>num</code> from the Skiplist and returns <code>true</code>. If <code>num</code> does not exist in the Skiplist, do nothing and return <code>false</code>. If there exist multiple <code>num</code> values, removing any one of them is fine.</li>
</ul>

<p>Note that duplicates may exist in the Skiplist, your code needs to handle this situation.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;Skiplist&quot;, &quot;add&quot;, &quot;add&quot;, &quot;add&quot;, &quot;search&quot;, &quot;add&quot;, &quot;search&quot;, &quot;erase&quot;, &quot;erase&quot;, &quot;search&quot;]
[[], [1], [2], [3], [0], [4], [1], [0], [1], [1]]
<strong>Output</strong>
[null, null, null, null, false, null, true, false, true, false]

<strong>Explanation</strong>
Skiplist skiplist = new Skiplist();
skiplist.add(1);
skiplist.add(2);
skiplist.add(3);
skiplist.search(0); // return False
skiplist.add(4);
skiplist.search(1); // return True
skiplist.erase(0);  // return False, 0 is not in skiplist.
skiplist.erase(1);  // return True
skiplist.search(1); // return False, 1 has already been erased.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= num, target &lt;= 2 * 10<sup>4</sup></code></li>
	<li>At most <code>5 * 10<sup>4</sup></code> calls will be made to <code>search</code>, <code>add</code>, and <code>erase</code>.</li>
</ul>

## Solutions

Because the `level` of the nodes is random, multiple `next` pointers are required, and the rest of the operation is like a single linked list.

<!-- tabs:start -->

### **Python3**

```python
class Node:
    def __init__(self, val: int, level: int):
        self.val = val
        self.next = [None for _ in range(level)]


class Skiplist:
    max_level = 16
    p = 0.5

    def __init__(self):
        self.head = Node(-1, self.max_level)
        self.level = 1

    def search(self, target: int) -> bool:
        p = self.head
        for i in range(self.level - 1, -1, -1):
            p = self.find_closest(p, i, target)
            if p.next[i] != None and p.next[i].val == target:
                return True
        return False

    def add(self, num: int) -> None:
        level = self.random_level()
        self.level = max(self.level, level)
        node = Node(num, level)
        p = self.head
        for i in range(self.level - 1, -1, -1):
            p = self.find_closest(p, i, num)
            if i < level:
                node.next[i] = p.next[i]
                p.next[i] = node

    def erase(self, num: int) -> bool:
        ok = False
        p = self.head
        for i in range(self.level - 1, -1, -1):
            p = self.find_closest(p, i, num)
            if p.next[i] != None and p.next[i].val == num:
                p.next[i] = p.next[i].next[i]
                ok = True
        while self.level > 1 and self.head.next[self.level - 1] == None:
            self.level -= 1
        return ok

    def find_closest(self, p: Node, level: int, target: int) -> Node:
        while p.next[level] != None and p.next[level].val < target:
            p = p.next[level]
        return p

    def random_level(self) -> int:
        level = 1
        while level < self.max_level and random.random() < self.p:
            level += 1
        return level


# Your Skiplist object will be instantiated and called as such:
# obj = Skiplist()
# param_1 = obj.search(target)
# obj.add(num)
# param_3 = obj.erase(num)
```

### **Java**

```java
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
```

### **Go**

```go
func init() { rand.Seed(time.Now().UnixNano()) }

const (
	maxLevel = 16
	p        = 0.5
)

type node struct {
	val  int
	next []*node
}

func newNode(val, level int) *node {
	return &node{
		val:  val,
		next: make([]*node, level),
	}
}

type Skiplist struct {
	head  *node
	level int
}

func Constructor() Skiplist {
	return Skiplist{
		head:  newNode(-1, maxLevel),
		level: 1,
	}
}

func (this *Skiplist) Search(target int) bool {
	p := this.head
	for i := this.level - 1; i >= 0; i-- {
		p = findClosest(p, i, target)
		if p.next[i] != nil && p.next[i].val == target {
			return true
		}
	}
	return false
}

func (this *Skiplist) Add(num int) {
	level := randomLevel()
	if level > this.level {
		this.level = level
	}
	node := newNode(num, level)
	p := this.head
	for i := this.level - 1; i >= 0; i-- {
		p = findClosest(p, i, num)
		if i < level {
			node.next[i] = p.next[i]
			p.next[i] = node
		}
	}
}

func (this *Skiplist) Erase(num int) bool {
	ok := false
	p := this.head
	for i := this.level - 1; i >= 0; i-- {
		p = findClosest(p, i, num)
		if p.next[i] != nil && p.next[i].val == num {
			p.next[i] = p.next[i].next[i]
			ok = true
		}
	}
	for this.level > 1 && this.head.next[this.level-1] == nil {
		this.level--
	}
	return ok
}

func findClosest(p *node, level, target int) *node {
	for p.next[level] != nil && p.next[level].val < target {
		p = p.next[level]
	}
	return p
}

func randomLevel() int {
	level := 1
	for level < maxLevel && rand.Float64() < p {
		level++
	}
	return level
}

/**
 * Your Skiplist object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Search(target);
 * obj.Add(num);
 * param_3 := obj.Erase(num);
 */
```

### **...**

```

```

<!-- tabs:end -->
