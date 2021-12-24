# [1206. 设计跳表](https://leetcode-cn.com/problems/design-skiplist)

[English Version](/solution/1200-1299/1206.Design%20Skiplist/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>不使用任何库函数，设计一个跳表。</p>

<p>跳表是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思想与链表相似。</p>

<p>例如，一个跳表包含 [30, 40, 50, 60, 70, 90]，然后增加 80、45 到跳表中，以下图的方式操作：</p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1206.Design%20Skiplist/images/1506_skiplist.gif" style="width: 500px;"><br>
<small>Artyom Kalinin [CC BY-SA 3.0], via <a href="https://commons.wikimedia.org/wiki/File:Skip_list_add_element-en.gif" target="_blank" title="Artyom Kalinin [CC BY-SA 3.0 (https://creativecommons.org/licenses/by-sa/3.0)], via Wikimedia Commons">Wikimedia Commons</a></small></p>

<p>跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。跳表的每一个操作的平均时间复杂度是 O(log(n))，空间复杂度是 O(n)。</p>

<p>在本题中，你的设计应该要包含这些函数：</p>

<ul>
	<li><code>bool search(int target)</code> : 返回target是否存在于跳表中。</li>
	<li><code>void add(int num)</code>:&nbsp;插入一个元素到跳表。</li>
	<li><code>bool erase(int num)</code>: 在跳表中删除一个值，如果&nbsp;<code>num</code>&nbsp;不存在，直接返回false. 如果存在多个&nbsp;<code>num</code>&nbsp;，删除其中任意一个即可。</li>
</ul>

<p>了解更多 :&nbsp;<a href="https://en.wikipedia.org/wiki/Skip_list" target="_blank">https://en.wikipedia.org/wiki/Skip_list</a></p>

<p>注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。</p>

<p><strong>样例:</strong></p>

<pre>Skiplist skiplist = new Skiplist();

skiplist.add(1);
skiplist.add(2);
skiplist.add(3);
skiplist.search(0);   // 返回 false
skiplist.add(4);
skiplist.search(1);   // 返回 true
skiplist.erase(0);    // 返回 false，0 不在跳表中
skiplist.erase(1);    // 返回 true
skiplist.search(1);   // 返回 false，1 已被擦除
</pre>

<p><strong>约束条件:</strong></p>

<ul>
	<li><code>0 &lt;= num, target&nbsp;&lt;= 20000</code></li>
	<li>最多调用&nbsp;<code>50000</code>&nbsp;次&nbsp;<code>search</code>, <code>add</code>, 以及&nbsp;<code>erase</code>操作。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

因为节点 `level` 随机，所以需要多个 `next` 指针，其余操作类似单链表

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
