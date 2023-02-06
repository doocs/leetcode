# [138. 复制带随机指针的链表](https://leetcode.cn/problems/copy-list-with-random-pointer)

[English Version](/solution/0100-0199/0138.Copy%20List%20with%20Random%20Pointer/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的链表，每个节点包含一个额外增加的随机指针 <code>random</code> ，该指针可以指向链表中的任何节点或空节点。</p>

<p>构造这个链表的&nbsp;<strong><a href="https://baike.baidu.com/item/深拷贝/22785317?fr=aladdin" target="_blank">深拷贝</a></strong>。&nbsp;深拷贝应该正好由 <code>n</code> 个 <strong>全新</strong> 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 <code>next</code> 指针和 <code>random</code> 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。<strong>复制链表中的指针都不应指向原链表中的节点 </strong>。</p>

<p>例如，如果原链表中有 <code>X</code> 和 <code>Y</code> 两个节点，其中 <code>X.random --&gt; Y</code> 。那么在复制链表中对应的两个节点 <code>x</code> 和 <code>y</code> ，同样有 <code>x.random --&gt; y</code> 。</p>

<p>返回复制链表的头节点。</p>

<p>用一个由&nbsp;<code>n</code>&nbsp;个节点组成的链表来表示输入/输出中的链表。每个节点用一个&nbsp;<code>[val, random_index]</code>&nbsp;表示：</p>

<ul>
	<li><code>val</code>：一个表示&nbsp;<code>Node.val</code>&nbsp;的整数。</li>
	<li><code>random_index</code>：随机指针指向的节点索引（范围从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n-1</code>）；如果不指向任何节点，则为&nbsp;&nbsp;<code>null</code>&nbsp;。</li>
</ul>

<p>你的代码 <strong>只</strong> 接受原链表的头节点 <code>head</code> 作为传入参数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0138.Copy%20List%20with%20Random%20Pointer/images/e1.png" style="height: 142px; width: 700px;" /></p>

<pre>
<strong>输入：</strong>head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
<strong>输出：</strong>[[7,null],[13,0],[11,4],[10,2],[1,0]]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0138.Copy%20List%20with%20Random%20Pointer/images/e2.png" style="height: 114px; width: 700px;" /></p>

<pre>
<strong>输入：</strong>head = [[1,1],[2,1]]
<strong>输出：</strong>[[1,1],[2,1]]
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0138.Copy%20List%20with%20Random%20Pointer/images/e3.png" style="height: 122px; width: 700px;" /></strong></p>

<pre>
<strong>输入：</strong>head = [[3,null],[3,0],[3,null]]
<strong>输出：</strong>[[3,null],[3,0],[3,null]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 1000</code><meta charset="UTF-8" /></li>
	<li><code>-10<sup>4</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>4</sup></code></li>
	<li><code>Node.random</code>&nbsp;为&nbsp;<code>null</code> 或指向链表中的节点。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

遍历链表，将链表中的每个节点都复制一份，然后将原节点和复制节点的对应关系存储在哈希表中，同时连接好复制节点的 $next$ 指针。

接下来再遍历链表，根据哈希表中存储的对应关系，将复制节点的 $random$ 指针连接好。

时间复杂度为 $O(n)$，空间复杂度为 $O(n)$。其中 $n$ 为链表的长度。

**方法二：拼接 + 拆分**

遍历链表，将链表中的每个节点都复制一份，然后将复制节点插入到原节点的后面。

接下来再遍历链表，根据原节点的 $random$ 指针，将复制节点的 $random$ 指针连接好。

最后再遍历链表，将链表拆分成原链表和复制链表。

时间复杂度为 $O(n)$，空间复杂度为 $O(1)$。其中 $n$ 为链表的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random
"""


class Solution:
    def copyRandomList(self, head: "Node") -> "Node":
        d = {}
        dummy = tail = Node(0)
        cur = head
        while cur:
            tail.next = Node(cur.val)
            tail = tail.next
            d[cur] = tail
            cur = cur.next
        tail = dummy.next
        cur = head
        while cur:
            tail.random = d.get(cur.random)
            tail = tail.next
            cur = cur.next
        return dummy.next
```

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random
"""


class Solution:
    def copyRandomList(self, head: "Node") -> "Node":
        if head is None:
            return None
        cur = head
        while cur:
            node = Node(cur.val, cur.next)
            cur.next = node
            cur = node.next

        cur = head
        while cur:
            if cur.random:
                cur.next.random = cur.random.next
            cur = cur.next.next

        ans = head.next
        cur = head
        while cur:
            nxt = cur.next
            if nxt:
                cur.next = nxt.next
            cur = nxt
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> d = new HashMap<>();
        Node dummy = new Node(0);
        Node tail = dummy;
        for (Node cur = head; cur != null; cur = cur.next) {
            tail.next = new Node(cur.val);
            tail = tail.next;
            d.put(cur, tail);
        }
        tail = dummy.next;
        for (Node cur = head; cur != null; cur = cur.next) {
            tail.random = d.get(cur.random);
            tail = tail.next;
        }
        return dummy.next;
    }
}
```

```java
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        for (Node cur = head; cur != null;) {
            Node node = new Node(cur.val, cur.next);
            cur.next = node;
            cur = node.next;
        }
        for (Node cur = head; cur != null; cur = cur.next.next) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
        }
        Node ans = head.next;
        for (Node cur = head; cur != null;) {
            Node nxt = cur.next;
            if (nxt != null) {
                cur.next = nxt.next;
            }
            cur = nxt;
        }
        return ans;
    }
}
```

### **C++**

```cpp
/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* next;
    Node* random;

    Node(int _val) {
        val = _val;
        next = NULL;
        random = NULL;
    }
};
*/
class Solution {
public:
    Node* copyRandomList(Node* head) {
        unordered_map<Node*, Node*> d;
        Node* dummy = new Node(0);
        Node* tail = dummy;
        for (auto cur = head; cur; cur = cur->next) {
            tail->next = new Node(cur->val);
            tail = tail->next;
            d[cur] = tail;
        }
        tail = dummy->next;
        for (auto cur = head; cur; cur = cur->next) {
            tail->random = d[cur->random];
            tail = tail->next;
        }
        return dummy->next;
    }
};
```

```cpp
/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* next;
    Node* random;

    Node(int _val) {
        val = _val;
        next = NULL;
        random = NULL;
    }
};
*/
class Solution {
public:
    Node* copyRandomList(Node* head) {
        if (!head) {
            return nullptr;
        }
        for (Node* cur = head; cur; ) {
            Node* node = new Node(cur->val);
            node->next = cur->next;
            cur->next = node;
            cur = node->next;
        }
        for (Node* cur = head; cur; cur = cur->next->next) {
            if (cur->random) {
                cur->next->random = cur->random->next;
            }
        }
        Node* ans = head->next;
        for (Node* cur = head; cur; ) {
            Node* nxt = cur->next;
            if (nxt) {
                cur->next = nxt->next;
            }
            cur = nxt;
        }
        return ans;
    }
};
```

### **Go**

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Next *Node
 *     Random *Node
 * }
 */

func copyRandomList(head *Node) *Node {
	d := map[*Node]*Node{}
	dummy := &Node{}
	tail := dummy
	for cur := head; cur != nil; cur = cur.Next {
		tail.Next = &Node{Val: cur.Val}
		tail = tail.Next
		d[cur] = tail
	}
	tail = dummy.Next
	for cur := head; cur != nil; cur = cur.Next {
		tail.Random = d[cur.Random]
		tail = tail.Next
	}
	return dummy.Next
}
```

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Next *Node
 *     Random *Node
 * }
 */

func copyRandomList(head *Node) *Node {
	if head == nil {
		return nil
	}
	for cur := head; cur != nil; {
		node := &Node{cur.Val, cur.Next, nil}
		cur.Next = node
		cur = node.Next
	}
	for cur := head; cur != nil; cur = cur.Next.Next {
		if cur.Random != nil {
			cur.Next.Random = cur.Random.Next
		}
	}
	ans := head.Next
	for cur := head; cur != nil; {
		nxt := cur.Next
		if nxt != nil {
			cur.Next = nxt.Next
		}
		cur = nxt
	}
	return ans
}
```

### **C#**

```cs
/*
// Definition for a Node.
public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int _val) {
        val = _val;
        next = null;
        random = null;
    }
}
*/

public class Solution {
    public Node CopyRandomList(Node head) {
        Dictionary<Node, Node> d = new Dictionary<Node, Node>();
        Node dummy = new Node(0);
        Node tail = dummy;
        for (Node cur = head; cur != null; cur = cur.next) {
            tail.next = new Node(cur.val);
            tail = tail.next;
            d[cur] = tail;
        }
        tail = dummy.next;
        for (Node cur = head; cur != null; cur = cur.next) {
            tail.random = cur.random == null ? null : d[cur.random];
            tail = tail.next;
        }
        return dummy.next;
    }
}
```

```cs
/*
// Definition for a Node.
public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int _val) {
        val = _val;
        next = null;
        random = null;
    }
}
*/

public class Solution {
    public Node CopyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        for (Node cur = head; cur != null; ) {
            Node node = new Node(cur.val, cur.next);
            cur.next = node;
            cur = node.next;
        }
        for (Node cur = head; cur != null; cur = cur.next.next) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
        }
        Node ans = head.next;
        for (Node cur = head; cur != null; ) {
            Node nxt = cur.next;
            if (nxt != null) {
                cur.next = nxt.next;
            }
            cur = nxt;
        }
        return ans;
    }
}
```

### **JavaScript**

```js
/**
 * // Definition for a Node.
 * function Node(val, next, random) {
 *    this.val = val;
 *    this.next = next;
 *    this.random = random;
 * };
 */

/**
 * @param {Node} head
 * @return {Node}
 */
var copyRandomList = function (head) {
    const d = new Map();
    const dummy = new Node(0);
    let tail = dummy;
    for (let cur = head; cur; cur = cur.next) {
        tail.next = new Node(cur.val);
        tail = tail.next;
        d.set(cur, tail);
    }
    tail = dummy.next;
    for (let cur = head; cur; cur = cur.next) {
        tail.random = d.get(cur.random);
        tail = tail.next;
    }
    return dummy.next;
};
```

```js
/**
 * // Definition for a Node.
 * function Node(val, next, random) {
 *    this.val = val;
 *    this.next = next;
 *    this.random = random;
 * };
 */

/**
 * @param {Node} head
 * @return {Node}
 */
var copyRandomList = function (head) {
    if (!head) {
        return null;
    }
    for (let cur = head; cur; ) {
        const node = new Node(cur.val, cur.next, null);
        cur.next = node;
        cur = node.next;
    }
    for (let cur = head; cur; cur = cur.next.next) {
        if (cur.random) {
            cur.next.random = cur.random.next;
        }
    }
    const ans = head.next;
    for (let cur = head; cur; ) {
        const nxt = cur.next;
        if (nxt) {
            cur.next = nxt.next;
        }
        cur = nxt;
    }
    return ans;
};
```

### **TypeScript**

```ts
/**
 * Definition for Node.
 * class Node {
 *     val: number
 *     next: Node | null
 *     random: Node | null
 *     constructor(val?: number, next?: Node, random?: Node) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *         this.random = (random===undefined ? null : random)
 *     }
 * }
 */

function copyRandomList(head: Node | null): Node | null {
    const map = new Map<Node, Node>();
    let cur = head;
    while (cur != null) {
        map.set(cur, new Node(cur.val));
        cur = cur.next;
    }
    cur = head;
    while (cur != null) {
        map.get(cur).next = map.get(cur.next) ?? null;
        map.get(cur).random = map.get(cur.random) ?? null;
        cur = cur.next;
    }
    return map.get(head);
}
```

### **...**

```

```

<!-- tabs:end -->
