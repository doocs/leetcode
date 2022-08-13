# [面试题 02.01. 移除重复节点](https://leetcode.cn/problems/remove-duplicate-node-lcci)

[English Version](/lcci/02.01.Remove%20Duplicate%20Node/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。</p>

<p> <strong>示例1:</strong></p>

<pre>
<strong> 输入</strong>：[1, 2, 3, 3, 2, 1]
<strong> 输出</strong>：[1, 2, 3]
</pre>

<p> <strong>示例2:</strong></p>

<pre>
<strong> 输入</strong>：[1, 1, 1, 1, 2]
<strong> 输出</strong>：[1, 2]
</pre>

<p><strong>提示：</strong></p>

<ol>
<li>链表长度在[0, 20000]范围内。</li>
<li>链表元素在[0, 20000]范围内。</li>
</ol>

<p> <strong>进阶：</strong></p>

<p>如果不得使用临时缓冲区，该怎么解决？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def removeDuplicateNodes(self, head: ListNode) -> ListNode:
        if head is None or head.next is None:
            return head
        cache = set()
        cache.add(head.val)
        cur, p = head, head.next
        while p:
            if p.val not in cache:
                cur.next = p
                cur = cur.next
                cache.add(p.val)
            p = p.next
        cur.next = None
        return head
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Set<Integer> s = new HashSet<>();
        s.add(head.val);
        ListNode cur = head;
        for (ListNode p = head.next; p != null; p = p.next) {
            if (!s.contains(p.val)) {
                cur.next = p;
                cur = cur.next;
                s.add(p.val);
            }
        }
        cur.next = null;
        return head;
    }
}
```

### **JavaScript**

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var removeDuplicateNodes = function (head) {
    if (head == null || head.next == null) return head;
    const cache = new Set([]);
    cache.add(head.val);
    let cur = head,
        fast = head.next;
    while (fast !== null) {
        if (!cache.has(fast.val)) {
            cur.next = fast;
            cur = cur.next;
            cache.add(fast.val);
        }
        fast = fast.next;
    }
    cur.next = null;
    return head;
};
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
    ListNode* removeDuplicateNodes(ListNode* head) {
        if (head == nullptr || head->next == nullptr) {
            return head;
        }
        unordered_set<int> cache = {head->val};
        ListNode* cur = head;
        for (ListNode* p = head->next; p != nullptr; p = p->next) {
            if (!cache.count(p->val)) {
                cur->next = p;
                cur = cur->next;
                cache.insert(p->val);
            }
        }
        cur->next = nullptr;
        return head;
    }
};
```

### **Go**

```go
func removeDuplicateNodes(head *ListNode) *ListNode {
	if head == nil {
		return nil
	}
	vis := map[int]bool{head.Val: true}
	p := head
	for p.Next != nil {
		if vis[p.Next.Val] {
			p.Next = p.Next.Next
		} else {
			vis[p.Next.Val] = true
			p = p.Next
		}
	}
	return head
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

function removeDuplicateNodes(head: ListNode | null): ListNode | null {
    if (head == null) {
        return head;
    }
    const set = new Set<number>([head.val]);
    let cur = head;
    while (cur.next != null) {
        if (set.has(cur.next.val)) {
            cur.next = cur.next.next;
        } else {
            set.add(cur.next.val);
            cur = cur.next;
        }
    }
    return head;
}
```

暴力（不推荐）

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

function removeDuplicateNodes(head: ListNode | null): ListNode | null {
    let n1 = head;
    while (n1 != null) {
        let n2 = n1;
        while (n2.next != null) {
            if (n1.val === n2.next.val) {
                n2.next = n2.next.next;
            } else {
                n2 = n2.next;
            }
        }
        n1 = n1.next;
    }
    return head;
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
use std::collections::HashSet;

impl Solution {
    pub fn remove_duplicate_nodes(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        match head {
            None => head,
            Some(mut head) => {
                let mut set = HashSet::new();
                set.insert(head.val);
                let mut pre = &mut head;
                while let Some(cur) = &pre.next {
                    if set.contains(&cur.val) {
                        pre.next = pre.next.take().unwrap().next;
                    } else {
                        set.insert(cur.val);
                        pre = pre.next.as_mut().unwrap();
                    }
                }
                Some(head)
            }
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
