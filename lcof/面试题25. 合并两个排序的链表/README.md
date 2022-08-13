# [面试题 25. 合并两个排序的链表](https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/)

## 题目描述

<p>输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。</p>

<p><strong>示例1：</strong></p>

<pre><strong>输入：</strong>1-&gt;2-&gt;4, 1-&gt;3-&gt;4
<strong>输出：</strong>1-&gt;1-&gt;2-&gt;3-&gt;4-&gt;4</pre>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= 链表长度 &lt;= 1000</code></p>

<p>注意：本题与主站 21 题相同：<a href="https://leetcode.cn/problems/merge-two-sorted-lists/">https://leetcode.cn/problems/merge-two-sorted-lists/</a></p>

## 解法

同时遍历两个链表，归并插入新链表中即可。

<!-- tabs:start -->

### **Python3**

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def mergeTwoLists(self, l1: ListNode, l2: ListNode) -> ListNode:
        dummy = ListNode(0)
        p = dummy
        while l1 and l2:
            if l1.val <= l2.val:
                p.next = l1
                l1 = l1.next
            else:
                p.next = l2
                l2 = l2.next
            p = p.next
        p.next = l1 or l2
        return dummy.next
```

### **Java**

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        p.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}
```

### **JavaScript**

-   递归

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */
var mergeTwoLists = function (l1, l2) {
    if (!(l1 && l2)) {
        return l1 || l2;
    }
    if (l1.val < l2.val) {
        l1.next = mergeTwoLists(l1.next, l2);
        return l1;
    } else {
        l2.next = mergeTwoLists(l2.next, l1);
        return l2;
    }
};
```

-   遍历

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */
var mergeTwoLists = function (l1, l2) {
    const res = new ListNode();
    let cur = res;
    while (l1 && l2) {
        let node;
        if (l1.val < l2.val) {
            node = l1;
            l1 = l1.next;
        } else {
            node = l2;
            l2 = l2.next;
        }
        cur.next = node;
        cur = node;
    }
    cur.next = l1 || l2;
    return res.next;
};
```

### **Go**

```go
func mergeTwoLists(l1 *ListNode, l2 *ListNode) *ListNode {
    if l1 == nil {
        return l2
    }
    if l2 == nil {
        return l1
    }
    if l1.Val <= l2.Val {
        l1.Next = mergeTwoLists(l1.Next,l2)
        return l1
    }
    l2.Next = mergeTwoLists(l1, l2.Next)
    return l2
}
```

### **C++**

```cpp
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */

class Solution {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        if (nullptr == l1 && nullptr == l2) {
            return nullptr;
        }

        if (nullptr == l1 || nullptr == l2) {
            return l1 == nullptr ? l2 : l1;
        }

        ListNode* node = nullptr;
        if (l1->val > l2->val) {
            node = l2;
            node->next = mergeTwoLists(l1, l2->next);
        } else {
            node = l1;
            node->next = mergeTwoLists(l1->next, l2);
        }

        return node;
    }
};
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

function mergeTwoLists(
    l1: ListNode | null,
    l2: ListNode | null,
): ListNode | null {
    const duumy = new ListNode();
    let cur = duumy;
    while (l1 && l2) {
        let node: ListNode;
        if (l1.val < l2.val) {
            node = l1;
            l1 = l1.next;
        } else {
            node = l2;
            l2 = l2.next;
        }
        cur.next = node;
        cur = node;
    }
    cur.next = l1 || l2;
    return duumy.next;
}
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

function mergeTwoLists(
    l1: ListNode | null,
    l2: ListNode | null,
): ListNode | null {
    if (l1 == null || l2 == null) {
        return l1 || l2;
    }
    if (l1.val < l2.val) {
        l1.next = mergeTwoLists(l1.next, l2);
        return l1;
    }
    l2.next = mergeTwoLists(l1, l2.next);
    return l2;
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
    pub fn merge_two_lists(
        l1: Option<Box<ListNode>>,
        l2: Option<Box<ListNode>>,
    ) -> Option<Box<ListNode>> {
        match (l1, l2) {
            (Some(mut n1), Some(mut n2)) => {
                if n1.val < n2.val {
                    n1.next = Self::merge_two_lists(n1.next, Some(n2));
                    Some(n1)
                } else {
                    n2.next = Self::merge_two_lists(Some(n1), n2.next);
                    Some(n2)
                }
            }
            (Some(node), None) => Some(node),
            (None, Some(node)) => Some(node),
            (None, None) => None,
        }
    }
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
impl Solution {
    pub fn merge_two_lists(
        mut l1: Option<Box<ListNode>>,
        mut l2: Option<Box<ListNode>>,
    ) -> Option<Box<ListNode>> {
        match (l1.is_some(), l2.is_some()) {
            (false, false) => None,
            (true, false) => l1,
            (false, true) => l2,
            (true, true) => {
                let mut dummy = Box::new(ListNode::new(0));
                let mut cur = &mut dummy;
                while l1.is_some() && l2.is_some() {
                    cur.next = if l1.as_ref().unwrap().val < l2.as_ref().unwrap().val {
                        let mut res = l1.take();
                        l1 = res.as_mut().unwrap().next.take();
                        res
                    } else {
                        let mut res = l2.take();
                        l2 = res.as_mut().unwrap().next.take();
                        res
                    };
                    cur = cur.next.as_mut().unwrap();
                }
                cur.next = if l1.is_some() { l1.take() } else { l2.take() };
                dummy.next.take()
            }
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
