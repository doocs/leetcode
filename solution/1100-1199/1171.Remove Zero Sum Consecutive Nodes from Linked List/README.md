# [1171. 从链表中删去总和值为零的连续节点](https://leetcode.cn/problems/remove-zero-sum-consecutive-nodes-from-linked-list)

[English Version](/solution/1100-1199/1171.Remove%20Zero%20Sum%20Consecutive%20Nodes%20from%20Linked%20List/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个链表的头节点&nbsp;<code>head</code>，请你编写代码，反复删去链表中由 <strong>总和</strong>&nbsp;值为 <code>0</code> 的连续节点组成的序列，直到不存在这样的序列为止。</p>

<p>删除完毕后，请你返回最终结果链表的头节点。</p>

<p>&nbsp;</p>

<p>你可以返回任何满足题目要求的答案。</p>

<p>（注意，下面示例中的所有序列，都是对&nbsp;<code>ListNode</code>&nbsp;对象序列化的表示。）</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>head = [1,2,-3,3,1]
<strong>输出：</strong>[3,1]
<strong>提示：</strong>答案 [1,2,1] 也是正确的。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>head = [1,2,3,-3,4]
<strong>输出：</strong>[1,2,4]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>head = [1,2,3,-3,-2]
<strong>输出：</strong>[1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>给你的链表中可能有 <code>1</code> 到&nbsp;<code>1000</code>&nbsp;个节点。</li>
	<li>对于链表中的每个节点，节点的值：<code>-1000 &lt;= node.val &lt;= 1000</code>.</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和 + 哈希表**

若链表节点的两个前缀和相等，说明两个前缀和之间的连续节点序列的和为 $0$，那么可以消去这部分连续节点。

第一次遍历链表，用哈希表 `last` 记录前缀和以及对应的链表节点，同一前缀和 $s$，**后者的链表节点覆盖前者**。

第二次遍历链表，若当前节点 `cur` 的前缀和 $s$ 在 `last` 出现，说明 `cur` 与 `last[s]` 之间的所有节点和为 $0$，直接修改 `cur` 的指向，`cur.next = last[s].next`，就删去了这部分和为 $0$ 的连续节点。继续往后遍历，删除所有和为 $0$ 的连续节点。

最后返回链表的头节点 `dummy.next`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为链表的长度。

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
    def removeZeroSumSublists(self, head: Optional[ListNode]) -> Optional[ListNode]:
        dummy = ListNode(next=head)
        last = {}
        s, cur = 0, dummy
        while cur:
            s += cur.val
            last[s] = cur
            cur = cur.next
        s, cur = 0, dummy
        while cur:
            s += cur.val
            cur.next = last[s].next
            cur = cur.next
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
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        Map<Integer, ListNode> last = new HashMap<>();
        int s = 0;
        ListNode cur = dummy;
        while (cur != null) {
            s += cur.val;
            last.put(s, cur);
            cur = cur.next;
        }
        s = 0;
        cur = dummy;
        while (cur != null) {
            s += cur.val;
            cur.next = last.get(s).next;
            cur = cur.next;
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
    ListNode* removeZeroSumSublists(ListNode* head) {
        ListNode* dummy = new ListNode(0, head);
        unordered_map<int, ListNode*> last;
        ListNode* cur = dummy;
        int s = 0;
        while (cur) {
            s += cur->val;
            last[s] = cur;
            cur = cur->next;
        }
        s = 0;
        cur = dummy;
        while (cur) {
            s += cur->val;
            cur->next = last[s]->next;
            cur = cur->next;
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
func removeZeroSumSublists(head *ListNode) *ListNode {
	dummy := &ListNode{0, head}
	last := map[int]*ListNode{}
	cur := dummy
	s := 0
	for cur != nil {
		s += cur.Val
		last[s] = cur
		cur = cur.Next
	}
	s = 0
	cur = dummy
	for cur != nil {
		s += cur.Val
		cur.Next = last[s].Next
		cur = cur.Next
	}
	return dummy.Next
}
```

### **...**

```

```

<!-- tabs:end -->
