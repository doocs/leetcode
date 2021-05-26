# [面试题 02.04. 分割链表](https://leetcode-cn.com/problems/partition-list-lcci)

[English Version](/lcci/02.04.Partition%20List/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>编写程序以 x 为基准分割链表，使得所有小于 x 的节点排在大于或等于 x 的节点之前。如果链表中包含 x，x 只需出现在小于 x 的元素之后(如下所示)。分割元素 x 只需处于&ldquo;右半部分&rdquo;即可，其不需要被置于左右两部分之间。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> head = 3-&gt;5-&gt;8-&gt;5-&gt;10-&gt;2-&gt;1, <em>x</em> = 5
<strong>输出:</strong> 3-&gt;1-&gt;2-&gt;10-&gt;5-&gt;5-&gt;8
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

创建两个链表，一个存放小于 `x` 的节点，另一个存放大于等于 `x` 的节点，之后进行拼接即可。

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
    def partition(self, head: ListNode, x: int) -> ListNode:
        l1, l2 = ListNode(0), ListNode(0)
        cur1, cur2 = l1, l2
        while head:
            if head.val < x:
                cur1.next = head
                cur1 = cur1.next
            else:
                cur2.next = head
                cur2 = cur2.next
            head = head.next
        cur1.next = l2.next
        cur2.next = None
        return l1.next
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
    public ListNode partition(ListNode head, int x) {
        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(0);
        ListNode cur1 = l1, cur2 = l2;
        while (head != null) {
            if (head.val < x) {
                cur1.next = head;
                cur1 = cur1.next;
            } else {
                cur2.next = head;
                cur2 = cur2.next;
            }
            head = head.next;
        }
        cur1.next = l2.next;
        cur2.next = null;
        return l1.next;
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
    ListNode* partition(ListNode* head, int x) {
        ListNode* l1 = new ListNode();
        ListNode* l2 = new ListNode();
        ListNode* cur1 = l1;
        ListNode* cur2 = l2;
        while (head != nullptr) {
            if (head->val < x) {
                cur1->next = head;
                cur1 = cur1->next;
            } else {
                cur2->next = head;
                cur2 = cur2->next;
            }
            head = head->next;
        }
        cur1->next = l2->next;
        cur2->next = nullptr;
        return l1->next;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
