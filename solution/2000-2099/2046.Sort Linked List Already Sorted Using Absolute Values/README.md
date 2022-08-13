# [2046. 给按照绝对值排序的链表排序](https://leetcode.cn/problems/sort-linked-list-already-sorted-using-absolute-values)

[English Version](/solution/2000-2099/2046.Sort%20Linked%20List%20Already%20Sorted%20Using%20Absolute%20Values/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

给你一个链表的头结点&nbsp;<code>head</code>&nbsp;，这个链表是根据结点的<strong>绝对值</strong>进行<strong>升序</strong>排序, 返回重新根据<strong>节点的值</strong>进行<strong>升序</strong>排序的链表。

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2046.Sort%20Linked%20List%20Already%20Sorted%20Using%20Absolute%20Values/images/image-20211017201240-3.png" style="width: 621px; height: 250px;">
<pre><strong>输入:</strong> head = [0,2,-5,5,10,-10]
<strong>输出:</strong> [-10,-5,0,2,5,10]
<strong>解释:</strong>
根据结点的值的绝对值排序的链表是 [0,2,-5,5,10,-10].
根据结点的值排序的链表是 [-10,-5,0,2,5,10].
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2046.Sort%20Linked%20List%20Already%20Sorted%20Using%20Absolute%20Values/images/image-20211017201318-4.png" style="width: 338px; height: 250px;"></p>

<pre><strong>输入:</strong> head = [0,1,2]
<strong>输出:</strong> [0,1,2]
<strong>解释:</strong>
这个链表已经是升序的了。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入:</strong> head = [1]
<strong>输出:</strong> [1]
<strong>解释:</strong>
这个链表已经是升序的了。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li>链表节点数的范围是&nbsp;<code>[1, 10<sup>5</sup>]</code>.</li>
	<li><code>-5000 &lt;= Node.val &lt;= 5000</code></li>
	<li><code>head</code>&nbsp;是根据结点绝对值升序排列好的链表.</li>
</ul>

<p>&nbsp;</p>
<strong>进阶:</strong>

<ul>
	<li>你可以在<code>O(n)</code>的时间复杂度之内解决这个问题吗?</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先默认第一个点已经排序完毕。然后从第二个点开始，遇到值为负数的节点，采用头插法；非负数，则继续往下遍历即可。

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
    def sortLinkedList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        prev, curr = head, head.next
        while curr:
            if curr.val < 0:
                t = curr.next
                prev.next = t
                curr.next = head
                head = curr
                curr = t
            else:
                prev, curr = curr, curr.next
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
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode sortLinkedList(ListNode head) {
        ListNode prev = head, curr = head.next;
        while (curr != null) {
            if (curr.val < 0) {
                ListNode t = curr.next;
                prev.next = t;
                curr.next = head;
                head = curr;
                curr = t;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }
        return head;
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
    ListNode* sortLinkedList(ListNode* head) {
        ListNode* prev = head;
        ListNode* curr = head->next;
        while (curr) {
            if (curr->val < 0) {
                auto t = curr->next;
                prev->next = t;
                curr->next = head;
                head = curr;
                curr = t;
            } else {
                prev = curr;
                curr = curr->next;
            }
        }
        return head;
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
func sortLinkedList(head *ListNode) *ListNode {
	prev, curr := head, head.Next
	for curr != nil {
		if curr.Val < 0 {
			t := curr.Next
			prev.Next = t
			curr.Next = head
			head = curr
			curr = t
		} else {
			prev, curr = curr, curr.Next
		}
	}
	return head
}
```

### **...**

```

```

<!-- tabs:end -->
