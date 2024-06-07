---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9822.%20%E9%93%BE%E8%A1%A8%E4%B8%AD%E5%80%92%E6%95%B0%E7%AC%ACk%E4%B8%AA%E8%8A%82%E7%82%B9/README.md
---

<!-- problem:start -->

# [面试题 22. 链表中倒数第 k 个节点](https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/)

## 题目描述

<!-- description:start -->

<p>输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。</p>

<p>例如，一个链表有 <code>6</code> 个节点，从头节点开始，它们的值依次是 <code>1、2、3、4、5、6</code>。这个链表的倒数第 <code>3</code> 个节点是值为 <code>4</code> 的节点。</p>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
给定一个链表: <strong>1->2->3->4->5</strong>, 和 <em>k </em><strong>= 2</strong>.

返回链表 4<strong>->5</strong>.</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：快慢指针

我们可以定义快慢指针 `fast` 和 `slow`，初始时均指向 `head`。

然后快指针 `fast` 先向前走 $k$ 步，然后快慢指针同时向前走，直到快指针走到链表尾部，此时慢指针指向的节点就是倒数第 $k$ 个节点。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为链表长度。

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def getKthFromEnd(self, head: ListNode, k: int) -> ListNode:
        slow = fast = head
        for _ in range(k):
            fast = fast.next
        while fast:
            slow, fast = slow.next, fast.next
        return slow
```

#### Java

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
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode slow = head, fast = head;
        while (k-- > 0) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
```

#### C++

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
    ListNode* getKthFromEnd(ListNode* head, int k) {
        ListNode *slow = head, *fast = head;
        while (k--) {
            fast = fast->next;
        }
        while (fast) {
            slow = slow->next;
            fast = fast->next;
        }
        return slow;
    }
};
```

#### Go

```go
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func getKthFromEnd(head *ListNode, k int) *ListNode {
	slow, fast := head, head
	for ; k > 0; k-- {
		fast = fast.Next
	}
	for fast != nil {
		slow, fast = slow.Next, fast.Next
	}
	return slow
}
```

#### Rust

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
    pub fn get_kth_from_end(head: Option<Box<ListNode>>, k: i32) -> Option<Box<ListNode>> {
        let mut fast = &head;
        for _ in 0..k {
            fast = &fast.as_ref().unwrap().next;
        }
        let mut slow = &head;
        while let (Some(nf), Some(ns)) = (fast, slow) {
            fast = &nf.next;
            slow = &ns.next;
        }
        slow.to_owned()
    }
}
```

#### JavaScript

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
 * @param {number} k
 * @return {ListNode}
 */
var getKthFromEnd = function (head, k) {
    let fast = head;
    while (k--) {
        fast = fast.next;
    }
    let slow = head;
    while (fast) {
        slow = slow.next;
        fast = fast.next;
    }
    return slow;
};
```

#### C#

```cs
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode GetKthFromEnd(ListNode head, int k) {
        ListNode fast = head, slow = head;
        while (k-- > 0) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
```

#### Swift

```swift
/* public class ListNode {
*     var val: Int
*     var next: ListNode?
*     init(_ val: Int) {
*         self.val = val
*         self.next = nil
*     }
* }
*/

class Solution {
    func getKthFromEnd(_ head: ListNode?, _ k: Int) -> ListNode? {
        var slow = head
        var fast = head
        var k = k

        while k > 0 {
            fast = fast?.next
            k -= 1
        }

        while fast != nil {
            slow = slow?.next
            fast = fast?.next
        }

        return slow
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
