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

<!-- 这里可写通用的实现逻辑 -->

**朴素解法：**

合并前后两个链表，结果放在后一个链表位置上，依次循环下去。最后返回链表数组的最后一个元素。

**分而治之：**

多个链表合并复杂，若只有两个或一个链表时，那么就如同 [21. 合并两个有序链表](https://leetcode.cn/problems/merge-two-sorted-lists/) 一样。

与归并排序同思路，不断拆散，最终合并即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def mergeKLists(self, lists: List[ListNode]) -> ListNode:
        n = len(lists)
        if n == 0:
            return None
        for i in range(n - 1):
            lists[i + 1] = self.mergeTwoLists(lists[i], lists[i + 1])
        return lists[-1]

    def mergeTwoLists(self, l1: ListNode, l2: ListNode) -> ListNode:
        dummy = ListNode()
        cur = dummy
        while l1 and l2:
            if l1.val <= l2.val:
                cur.next = l1
                l1 = l1.next
            else:
                cur.next = l2
                l2 = l2.next
            cur = cur.next
        cur.next = l1 or l2
        return dummy.next
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
        int n = lists.length;
        if (n == 0) {
            return null;
        }
        for (int i = 0; i < n - 1; ++i) {
            lists[i + 1] = mergeLists(lists[i], lists[i + 1]);
        }
        return lists[n - 1];
    }

    private ListNode mergeLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}
```

### **C++**

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
        int n = lists.size();
        if (n == 0) return nullptr;
        for (int i = 1; i < n; ++i) lists[i] = mergeTwoLists(lists[i - 1], lists[i]);
        return lists[n - 1];
    }

private:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        ListNode* dummy = new ListNode();
        ListNode* cur = dummy;
        while (l1 && l2) {
            if (l1->val <= l2->val) {
                cur->next = l1;
                l1 = l1->next;
            } else {
                cur->next = l2;
                l2 = l2->next;
            }
            cur = cur->next;
        }
        cur->next = l1 ? l1 : l2;
        return dummy->next;
    }
};
```

### **Go**

```go
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func mergeKLists(lists []*ListNode) *ListNode {
    n := len(lists)
    if n == 0 {
        return nil
    }
    for i := 1; i < n; i++ {
        lists[i] = mergeTwoLists(lists[i-1], lists[i])
    }
    return lists[n-1]
}

 func mergeTwoLists(l1 *ListNode, l2 *ListNode) *ListNode {
    dummy := &ListNode{}
    cur := dummy
    for l1 != nil && l2 != nil {
        if l1.Val <= l2.Val {
            cur.Next = l1
            l1 = l1.Next
        } else {
            cur.Next = l2
            l2 = l2.Next
        }
        cur = cur.Next
    }
    if l1 != nil {
        cur.Next = l1
    } else if l2 != nil {
        cur.Next = l2
    }
    return dummy.Next
}
```

### **JavaScript**

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
    const n = lists.length;
    if (n == 0) {
        return null;
    }
    for (let i = 1; i < n; ++i) {
        lists[i] = mergeTwoLists(lists[i - 1], lists[i]);
    }
    return lists[n - 1];
};

function mergeTwoLists(l1, l2) {
    const dummy = new ListNode();
    let cur = dummy;
    while (l1 && l2) {
        if (l1.val <= l2.val) {
            cur.next = l1;
            l1 = l1.next;
        } else {
            cur.next = l2;
            l2 = l2.next;
        }
        cur = cur.next;
    }
    cur.next = l1 || l2;
    return dummy.next;
}
```

### **Ruby**

```rb
# Definition for singly-linked list.
# class ListNode
#     attr_accessor :val, :next
#     def initialize(val = 0, _next = nil)
#         @val = val
#         @next = _next
#     end
# end
# @param {ListNode[]} lists
# @return {ListNode}
def merge_k_lists(lists)
    n = lists.length
    i = 1
    while i < n
        lists[i] = merge_two_lists(lists[i - 1], lists[i])
        i += 1
    end
    lists[n - 1]
end

def merge_two_lists(l1, l2)
  dummy = ListNode.new()
  cur = dummy
  while l1 && l2
      if l1.val <= l2.val
          cur.next = l1
          l1 = l1.next
      else
          cur.next = l2
          l2 = l2.next
      end
      cur = cur.next
  end
  cur.next = l1 || l2
  dummy.next
end
```

### **C#**

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
        int n = lists.Length;
        if (n == 0) {
            return null;
        }
        for (int i = 1; i < n; ++i) {
            lists[i] = MergeTwoLists(lists[i - 1], lists[i]);
        }
        return lists[n - 1];
    }

    private ListNode MergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}
```

### **TypeScript**

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
    const n = lists.length;
    const dfs = (start: number, end: number) => {
        if (end - start <= 1) {
            return lists[start] ?? null;
        }

        const mid = (start + end) >> 1;
        let left = dfs(start, mid);
        let right = dfs(mid, end);

        const dummy = new ListNode();
        let cur = dummy;
        while (left || right) {
            let next: ListNode;
            if (
                (left ?? { val: Infinity }).val <
                (right ?? { val: Infinity }).val
            ) {
                next = left;
                left = left.next;
            } else {
                next = right;
                right = right.next;
            }
            cur.next = next;
            cur = next;
        }
        return dummy.next;
    };
    return dfs(0, n);
}
```

### **Rust**

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
impl Solution {
    pub fn merge_k_lists(mut lists: Vec<Option<Box<ListNode>>>) -> Option<Box<ListNode>> {
        let n = lists.len();
        Self::dfs(&mut lists, 0, n)
    }

    fn dfs(
        lists: &mut Vec<Option<Box<ListNode>>>,
        start: usize,
        end: usize,
    ) -> Option<Box<ListNode>> {
        if end - start <= 1 {
            if lists.get(start).is_some() {
                return lists[start].take();
            }
            return None;
        }
        let mid = start + (end - start) / 2;
        let mut left = Self::dfs(lists, start, mid);
        let mut right = Self::dfs(lists, mid, end);
        let mut dummy = Box::new(ListNode::new(0));
        let mut cur = &mut dummy;
        while left.is_some() || right.is_some() {
            let mut next = None;
            if left.is_some()
                && (right.is_none() || left.as_ref().unwrap().val < right.as_ref().unwrap().val)
            {
                let t = left.as_mut().unwrap().next.take();
                next = left.take();
                left = t;
            } else {
                let t = right.as_mut().unwrap().next.take();
                next = right.take();
                right = t;
            }
            cur.next = next;
            cur = cur.next.as_mut().unwrap();
        }
        dummy.next
    }
}
```

### **...**

```

```

<!-- tabs:end -->
