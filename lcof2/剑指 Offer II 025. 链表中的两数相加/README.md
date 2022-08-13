# [剑指 Offer II 025. 链表中的两数相加](https://leetcode.cn/problems/lMSNwu)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个 <strong>非空链表</strong> <code>l1</code>和 <code>l2</code>&nbsp;来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。</p>

<p>可以假设除了数字 0 之外，这两个数字都不会以零开头。</p>

<p>&nbsp;</p>

<p><strong>示例1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20025.%20%E9%93%BE%E8%A1%A8%E4%B8%AD%E7%9A%84%E4%B8%A4%E6%95%B0%E7%9B%B8%E5%8A%A0/images/1626420025-fZfzMX-image.png" style="width: 302px; " /></p>

<pre>
<strong>输入：</strong>l1 = [7,2,4,3], l2 = [5,6,4]
<strong>输出：</strong>[7,8,0,7]
</pre>

<p><strong>示例2：</strong></p>

<pre>
<strong>输入：</strong>l1 = [2,4,3], l2 = [5,6,4]
<strong>输出：</strong>[8,0,7]
</pre>

<p><strong>示例3：</strong></p>

<pre>
<strong>输入：</strong>l1 = [0], l2 = [0]
<strong>输出：</strong>[0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>链表的长度范围为<code> [1, 100]</code></li>
	<li><code>0 &lt;= node.val &lt;= 9</code></li>
	<li>输入数据保证链表代表的数字无前导 0</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>如果输入链表不能修改该如何处理？换句话说，不能对列表中的节点进行翻转。</p>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 445&nbsp;题相同：<a href="https://leetcode.cn/problems/add-two-numbers-ii/">https://leetcode.cn/problems/add-two-numbers-ii/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

利用栈将数字逆序。

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
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        s1, s2 = [], []
        while l1:
            s1.append(l1.val)
            l1 = l1.next
        while l2:
            s2.append(l2.val)
            l2 = l2.next
        carry, dummy = 0, ListNode()
        while s1 or s2 or carry:
            carry += (0 if not s1 else s1.pop()) + (0 if not s2 else s2.pop())
            # 创建结点，利用头插法将结点插入链表
            node = ListNode(carry % 10, dummy.next)
            dummy.next = node
            carry //= 10
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> s1 = new ArrayDeque<>();
        Deque<Integer> s2 = new ArrayDeque<>();
        for (; l1 != null; l1 = l1.next) {
            s1.push(l1.val);
        }
        for (; l2 != null; l2 = l2.next) {
            s2.push(l2.val);
        }
        int carry = 0;
        ListNode dummy = new ListNode();
        while (!s1.isEmpty() || !s2.isEmpty() || carry != 0) {
            carry += (s1.isEmpty() ? 0 : s1.pop()) + (s2.isEmpty() ? 0 : s2.pop());
            ListNode node = new ListNode(carry % 10, dummy.next);
            dummy.next = node;
            carry /= 10;
        }
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
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        stack<int> s1;
        stack<int> s2;
        for (; l1; l1 = l1->next) s1.push(l1->val);
        for (; l2; l2 = l2->next) s2.push(l2->val);
        int carry = 0;
        ListNode* dummy = new ListNode();
        while (!s1.empty() || !s2.empty() || carry) {
            if (!s1.empty()) {
                carry += s1.top();
                s1.pop();
            }
            if (!s2.empty()) {
                carry += s2.top();
                s2.pop();
            }
            ListNode* node = new ListNode(carry % 10, dummy->next);
            dummy->next = node;
            carry /= 10;
        }
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
func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	s1, s2 := arraystack.New(), arraystack.New()
	for l1 != nil {
		s1.Push(l1.Val)
		l1 = l1.Next
	}
	for l2 != nil {
		s2.Push(l2.Val)
		l2 = l2.Next
	}
	carry, dummy := 0, new(ListNode)
	for !s1.Empty() || !s2.Empty() || carry > 0 {
		v, ok := s1.Pop()
		if ok {
			carry += v.(int)
		}
		v, ok = s2.Pop()
		if ok {
			carry += v.(int)
		}
		node := &ListNode{Val: carry % 10, Next: dummy.Next}
		dummy.Next = node
		carry /= 10
	}
	return dummy.Next
}
```

### **...**

```

```

<!-- tabs:end -->
