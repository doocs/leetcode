---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0023.Merge%20k%20Sorted%20Lists/README.md
tags:
    - 链表
    - 分治
    - 堆（优先队列）
    - 归并排序
---

# [23. 合并 K 个升序链表](https://leetcode.cn/problems/merge-k-sorted-lists)

[English Version](/solution/0000-0099/0023.Merge%20k%20Sorted%20Lists/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个链表数组，每个链表都已经按升序排列。</p>

<p>请你将所有链表合并到一个升序链表中，返回合并后的链表。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>lists = [[1,4,5],[1,3,4],[2,6]]
<strong>输出：</strong>[1,1,2,3,4,4,5,6]
<strong>解释：</strong>链表数组如下：
[
  1-&gt;4-&gt;5,
  1-&gt;3-&gt;4,
  2-&gt;6
]
将它们合并到一个有序链表中得到。
1-&gt;1-&gt;2-&gt;3-&gt;4-&gt;4-&gt;5-&gt;6
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>lists = []
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>lists = [[]]
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>k == lists.length</code></li>
	<li><code>0 &lt;= k &lt;= 10^4</code></li>
	<li><code>0 &lt;= lists[i].length &lt;= 500</code></li>
	<li><code>-10^4 &lt;= lists[i][j] &lt;= 10^4</code></li>
	<li><code>lists[i]</code> 按 <strong>升序</strong> 排列</li>
	<li><code>lists[i].length</code> 的总和不超过 <code>10^4</code></li>
</ul>

## 解法

### 方法一：优先队列（小根堆）

我们可以创建一个小根堆来 $pq$ 维护所有链表的头节点，每次从小根堆中取出值最小的节点，添加到结果链表的末尾，然后将该节点的下一个节点加入堆中，重复上述步骤直到堆为空。

时间复杂度 $O(n \times \log k)$，空间复杂度 $O(k)$。其中 $n$ 是所有链表节点数目的总和，而 $k$ 是题目给定的链表数目。

<!-- tabs:start -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        setattr(ListNode, "__lt__", lambda a, b: a.val < b.val)
        pq = [head for head in lists if head]
        heapify(pq)
        dummy = cur = ListNode()
        while pq:
            node = heappop(pq)
            if node.next:
                heappush(pq, node.next)
            cur.next = node
            cur = cur.next
        return dummy.next
```

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode head : lists) {
            if (head != null) {
                pq.offer(head);
            }
        }
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            if (node.next != null) {
                pq.offer(node.next);
            }
            cur.next = node;
            cur = cur.next;
        }
        return dummy.next;
    }
}
```

```cpp
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        auto cmp = [](ListNode* a, ListNode* b) { return a->val > b->val; };
        priority_queue<ListNode*, vector<ListNode*>, decltype(cmp)> pq;
        for (auto head : lists) {
            if (head) {
                pq.push(head);
            }
        }
        ListNode* dummy = new ListNode();
        ListNode* cur = dummy;
        while (!pq.empty()) {
            ListNode* node = pq.top();
            pq.pop();
            if (node->next) {
                pq.push(node->next);
            }
            cur->next = node;
            cur = cur->next;
        }
        return dummy->next;
    }
};
```

```go
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func mergeKLists(lists []*ListNode) *ListNode {
	pq := hp{}
	for _, head := range lists {
		if head != nil {
			pq = append(pq, head)
		}
	}
	heap.Init(&pq)
	dummy := &ListNode{}
	cur := dummy
	for len(pq) > 0 {
		cur.Next = heap.Pop(&pq).(*ListNode)
		cur = cur.Next
		if cur.Next != nil {
			heap.Push(&pq, cur.Next)
		}
	}
	return dummy.Next
}

type hp []*ListNode

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].Val < h[j].Val }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(*ListNode)) }
func (h *hp) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

```ts
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     val: number
 *     next: ListNode | null
 *     constructor(val?: number, next?: ListNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *     }
 * }
 */

function mergeKLists(lists: Array<ListNode | null>): ListNode | null {
    const pq = new MinPriorityQueue({ priority: (node: ListNode) => node.val });
    for (const head of lists) {
        if (head) {
            pq.enqueue(head);
        }
    }
    const dummy: ListNode = new ListNode();
    let cur: ListNode = dummy;
    while (!pq.isEmpty()) {
        const node = pq.dequeue().element;
        cur.next = node;
        cur = cur.next;
        if (node.next) {
            pq.enqueue(node.next);
        }
    }
    return dummy.next;
}
```

```rust
// Definition for singly-linked list.
// #[derive(PartialEq, Eq, Clone, Debug)]
// pub struct ListNode {
//   pub val: i32,
//   pub next: Option<Box<ListNode>>
// }
//
// impl ListNode {
//   #[inline]
//   fn new(val: i32) -> Self {
//     ListNode {
//       next: None,
//       val
//     }
//   }
// }
use std::cmp::Ordering;
use std::collections::BinaryHeap;

impl PartialOrd for ListNode {
    fn partial_cmp(&self, other: &Self) -> Option<Ordering> {
        Some(self.cmp(other))
    }
}
impl Ord for ListNode {
    fn cmp(&self, other: &Self) -> Ordering {
        self.val.cmp(&other.val).reverse()
    }
}
impl Solution {
    pub fn merge_k_lists(lists: Vec<Option<Box<ListNode>>>) -> Option<Box<ListNode>> {
        let mut pq = lists
            .into_iter()
            .filter_map(|head| head)
            .collect::<BinaryHeap<_>>();
        let mut head = None;
        let mut cur = &mut head;
        while let Some(node) = pq.pop() {
            cur = &mut cur.insert(Box::new(ListNode::new(node.val))).next;
            if let Some(next) = node.next {
                pq.push(next);
            }
        }
        head
    }
}
```

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode[]} lists
 * @return {ListNode}
 */
var mergeKLists = function (lists) {
    const pq = new MinPriorityQueue({ priority: node => node.val });
    for (const head of lists) {
        if (head) {
            pq.enqueue(head);
        }
    }
    const dummy = new ListNode();
    let cur = dummy;
    while (!pq.isEmpty()) {
        const node = pq.dequeue().element;
        cur.next = node;
        cur = cur.next;
        if (node.next) {
            pq.enqueue(node.next);
        }
    }
    return dummy.next;
};
```

```cs
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int val=0, ListNode next=null) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */
public class Solution {
    public ListNode MergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode, int> pq = new PriorityQueue<ListNode, int>();
        foreach (var head in lists) {
            if (head != null) {
                pq.Enqueue(head, head.val);
            }
        }
        var dummy = new ListNode();
        var cur = dummy;
        while (pq.Count > 0) {
            var node = pq.Dequeue();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) {
                pq.Enqueue(node.next, node.next.val);
            }
        }
        return dummy.next;
    }
}
```

```php
# Definition for singly-linked list.
class ListNode {
    public $val;
    public $next;
    public function __construct($val = 0, $next = null) {
        $this->val = $val;
        $this->next = $next;
    }
}

class Solution {
    /**
     * @param ListNode[] $lists
     * @return ListNode
     */

    function mergeKLists($lists) {
        $numLists = count($lists);

        if ($numLists === 0) {
            return null;
        }
        while ($numLists > 1) {
            $mid = intval($numLists / 2);
            for ($i = 0; $i < $mid; $i++) {
                $lists[$i] = $this->mergeTwoLists($lists[$i], $lists[$numLists - $i - 1]);
            }
            $numLists = intval(($numLists + 1) / 2);
        }
        return $lists[0];
    }

    function mergeTwoLists($list1, $list2) {
        $dummy = new ListNode(0);
        $current = $dummy;

        while ($list1 != null && $list2 != null) {
            if ($list1->val <= $list2->val) {
                $current->next = $list1;
                $list1 = $list1->next;
            } else {
                $current->next = $list2;
                $list2 = $list2->next;
            }
            $current = $current->next;
        }
        if ($list1 != null) {
            $current->next = $list1;
        } elseif ($list2 != null) {
            $current->next = $list2;
        }
        return $dummy->next;
    }
}
```

<!-- tabs:end -->

<!-- end -->
