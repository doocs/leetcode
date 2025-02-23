---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1206.Design%20Skiplist/README.md
tags:
    - 设计
    - 链表
---

<!-- problem:start -->

# [1206. 设计跳表](https://leetcode.cn/problems/design-skiplist)

[English Version](/solution/1200-1299/1206.Design%20Skiplist/README_EN.md)

## 题目描述

<!-- description:start -->

<p>不使用任何库函数，设计一个 <strong>跳表</strong> 。</p>

<p><strong>跳表</strong> 是在 <code>O(log(n))</code> 时间内完成增加、删除、搜索操作的数据结构。跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思想与链表相似。</p>

<p>例如，一个跳表包含 <code>[30, 40, 50, 60, 70, 90]</code> ，然后增加 <code>80</code>、<code>45</code> 到跳表中，以下图的方式操作：</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1206.Design%20Skiplist/images/1702370216-mKQcTt-1506_skiplist.gif" style="width: 500px; height: 173px;" /></p>

<p>跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 <code>O(n)</code>。跳表的每一个操作的平均时间复杂度是 <code>O(log(n))</code>，空间复杂度是 <code>O(n)</code>。</p>

<p>了解更多 :&nbsp;<a href="https://oi-wiki.org/ds/skiplist/" target="_blank">https://oi-wiki.org/ds/skiplist/</a></p>

<p>在本题中，你的设计应该要包含这些函数：</p>

<ul>
	<li><code>bool search(int target)</code> : 返回target是否存在于跳表中。</li>
	<li><code>void add(int num)</code>:&nbsp;插入一个元素到跳表。</li>
	<li><code>bool erase(int num)</code>: 在跳表中删除一个值，如果&nbsp;<code>num</code>&nbsp;不存在，直接返回false. 如果存在多个&nbsp;<code>num</code>&nbsp;，删除其中任意一个即可。</li>
</ul>

<p>注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<b>输入</b>
["Skiplist", "add", "add", "add", "search", "add", "search", "erase", "erase", "search"]
[[], [1], [2], [3], [0], [4], [1], [0], [1], [1]]
<strong>输出</strong>
[null, null, null, null, false, null, true, false, true, false]

<strong>解释</strong>
Skiplist skiplist = new Skiplist();
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

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>0 &lt;= num, target &lt;= 2 * 10<sup>4</sup></code></li>
	<li>调用<code>search</code>, <code>add</code>, &nbsp;<code>erase</code>操作次数不大于&nbsp;<code>5 * 10<sup>4</sup></code>&nbsp;</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数据结构

跳表的核心思想是使用多个“层级”来存储数据，每一层都相当于一个索引。数据从底层的链表开始逐渐上升到更高层级的链表，最终形成一个多层链表结构。每一层的节点只包含部分数据，这样就可以通过跳跃来减少查找的时间。

在这个问题中，我们使用一个 $\textit{Node}$ 类来表示跳表的节点，每个节点包含一个 $\textit{val}$ 域和一个 $\textit{next}$ 数组，数组的长度为 $\textit{level}$，表示当前节点在每一层的下一个节点。我们使用一个 $\textit{Skiplist}$ 类来实现跳表的操作。

跳表包含一个头节点 $\textit{head}$ 和当前的最大层数 $\textit{level}$。头节点的值设为 $-1$，用于标识链表的起始位置。我们使用一个动态数组 $\textit{next}$ 来存储指向后继节点的指针。

对于 $\textit{search}$ 操作，我们从跳表的最高层开始，逐层向下遍历，直到找到目标节点或者确定目标节点不存在。每层都通过 $\textit{find\_closest}$ 方法跳跃到最接近目标的节点。

对于 $\textit{add}$ 操作，我们首先随机决定新节点的层数。然后，从最高层开始，逐层找到每层中最接近新值的节点，并在相应位置插入新节点。如果插入的层数大于当前跳表的最大层数，我们需要更新跳表的层数。

对于 $\textit{erase}$ 操作，类似于查找操作，遍历跳表的每一层，找到需要删除的节点并删除它。删除节点时需要更新每一层的 $\textit{next}$ 指针。如果跳表的最高层没有节点，则需要减少跳表的层数。

另外，我们定义了一个 $\textit{random\_level}$ 方法来随机决定新节点的层数。该方法会生成一个 $[1, \textit{max\_level}]$ 之间的随机数，直到生成的随机数大于等于 $\textit{p}$ 为止。还有一个 $\textit{find\_closest}$ 方法用于查找每一层中最接近目标值的节点。

上述操作的时间复杂度为 $O(\log n)$，其中 $n$ 为跳表的节点数。空间复杂度为 $O(n)$。

<!-- tabs:start -->

#### Python3

```python
class Node:
    __slots__ = ['val', 'next']

    def __init__(self, val: int, level: int):
        self.val = val
        self.next = [None] * level


class Skiplist:
    max_level = 32
    p = 0.25

    def __init__(self):
        self.head = Node(-1, self.max_level)
        self.level = 0

    def search(self, target: int) -> bool:
        curr = self.head
        for i in range(self.level - 1, -1, -1):
            curr = self.find_closest(curr, i, target)
            if curr.next[i] and curr.next[i].val == target:
                return True
        return False

    def add(self, num: int) -> None:
        curr = self.head
        level = self.random_level()
        node = Node(num, level)
        self.level = max(self.level, level)
        for i in range(self.level - 1, -1, -1):
            curr = self.find_closest(curr, i, num)
            if i < level:
                node.next[i] = curr.next[i]
                curr.next[i] = node

    def erase(self, num: int) -> bool:
        curr = self.head
        ok = False
        for i in range(self.level - 1, -1, -1):
            curr = self.find_closest(curr, i, num)
            if curr.next[i] and curr.next[i].val == num:
                curr.next[i] = curr.next[i].next[i]
                ok = True
        while self.level > 1 and self.head.next[self.level - 1] is None:
            self.level -= 1
        return ok

    def find_closest(self, curr: Node, level: int, target: int) -> Node:
        while curr.next[level] and curr.next[level].val < target:
            curr = curr.next[level]
        return curr

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

#### Java

```java
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
```

#### C++

```cpp
struct Node {
    int val;
    vector<Node*> next;
    Node(int v, int level)
        : val(v)
        , next(level, nullptr) {}
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
```

#### Go

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

#### TypeScript

```ts
class Node {
    val: number;
    next: (Node | null)[];

    constructor(val: number, level: number) {
        this.val = val;
        this.next = Array(level).fill(null);
    }
}

class Skiplist {
    private static maxLevel: number = 32;
    private static p: number = 0.25;
    private head: Node;
    private level: number;

    constructor() {
        this.head = new Node(-1, Skiplist.maxLevel);
        this.level = 0;
    }

    search(target: number): boolean {
        let curr = this.head;
        for (let i = this.level - 1; i >= 0; i--) {
            curr = this.findClosest(curr, i, target);
            if (curr.next[i] && curr.next[i]!.val === target) {
                return true;
            }
        }
        return false;
    }

    add(num: number): void {
        let curr = this.head;
        const level = this.randomLevel();
        const node = new Node(num, level);
        this.level = Math.max(this.level, level);

        for (let i = this.level - 1; i >= 0; i--) {
            curr = this.findClosest(curr, i, num);
            if (i < level) {
                node.next[i] = curr.next[i];
                curr.next[i] = node;
            }
        }
    }

    erase(num: number): boolean {
        let curr = this.head;
        let ok = false;

        for (let i = this.level - 1; i >= 0; i--) {
            curr = this.findClosest(curr, i, num);
            if (curr.next[i] && curr.next[i]!.val === num) {
                curr.next[i] = curr.next[i]!.next[i];
                ok = true;
            }
        }

        while (this.level > 1 && this.head.next[this.level - 1] === null) {
            this.level--;
        }

        return ok;
    }

    private findClosest(curr: Node, level: number, target: number): Node {
        while (curr.next[level] && curr.next[level]!.val < target) {
            curr = curr.next[level]!;
        }
        return curr;
    }

    private randomLevel(): number {
        let level = 1;
        while (level < Skiplist.maxLevel && Math.random() < Skiplist.p) {
            level++;
        }
        return level;
    }
}

/**
 * Your Skiplist object will be instantiated and called as such:
 * var obj = new Skiplist()
 * var param_1 = obj.search(target)
 * obj.add(num)
 * var param_3 = obj.erase(num)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
