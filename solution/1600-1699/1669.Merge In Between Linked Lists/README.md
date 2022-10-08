# [1669. 合并两个链表](https://leetcode.cn/problems/merge-in-between-linked-lists)

[English Version](/solution/1600-1699/1669.Merge%20In%20Between%20Linked%20Lists/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个链表&nbsp;<code>list1</code> 和&nbsp;<code>list2</code>&nbsp;，它们包含的元素分别为&nbsp;<code>n</code> 个和&nbsp;<code>m</code> 个。</p>

<p>请你将&nbsp;<code>list1</code>&nbsp;中下标从 <code>a</code> 到 <code>b</code> 的全部节点都删除，并将<code>list2</code>&nbsp;接在被删除节点的位置。</p>

<p>下图中蓝色边和节点展示了操作后的结果：</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1669.Merge%20In%20Between%20Linked%20Lists/images/fig1.png" style="height: 130px; width: 504px;" />
<p>请你返回结果链表的头指针。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1669.Merge%20In%20Between%20Linked%20Lists/images/merge_linked_list_ex1.png" style="width: 406px; height: 140px;" /></p>

<pre>
<b>输入：</b>list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
<b>输出：</b>[0,1,2,1000000,1000001,1000002,5]
<b>解释：</b>我们删除 list1 中下标为 3 和 4 的两个节点，并将 list2 接在该位置。上图中蓝色的边和节点为答案链表。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1669.Merge%20In%20Between%20Linked%20Lists/images/merge_linked_list_ex2.png" style="width: 463px; height: 140px;" />
<pre>
<b>输入：</b>list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
<b>输出：</b>[0,1,1000000,1000001,1000002,1000003,1000004,6]
<b>解释：</b>上图中蓝色的边和节点为答案链表。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= list1.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= a &lt;= b &lt; list1.length - 1</code></li>
	<li><code>1 &lt;= list2.length &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

直接模拟题目中的操作即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为链表 `list1` 的长度。

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
    def mergeInBetween(
        self, list1: ListNode, a: int, b: int, list2: ListNode
    ) -> ListNode:
        p = q = list1
        for _ in range(a - 1):
            p = p.next
        for _ in range(b + 1):
            q = q.next
        t = list2
        while t.next:
            t = t.next
        t.next = q
        p.next = list2
        return list1
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
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode p = list1, q = list1;
        for (int i = 0; i < a - 1; ++i) {
            p = p.next;
        }
        for (int i = 0; i < b + 1; ++i) {
            q = q.next;
        }
        ListNode t = list2;
        while (t.next != null) {
            t = t.next;
        }
        t.next = q;
        p.next = list2;
        return list1;
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
    ListNode* mergeInBetween(ListNode* list1, int a, int b, ListNode* list2) {
        auto p = list1, q = list1;
        for (int i = 0; i < a - 1; ++i) {
            p = p->next;
        }
        for (int i = 0; i < b + 1; ++i) {
            q = q->next;
        }
        auto t = list2;
        while (t->next) {
            t = t->next;
        }
        t->next = q;
        p->next = list2;
        return list1;
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
func mergeInBetween(list1 *ListNode, a int, b int, list2 *ListNode) *ListNode {
	p, q := list1, list1
	for i := 0; i < a-1; i++ {
		p = p.Next
	}
	for i := 0; i < b+1; i++ {
		q = q.Next
	}
	t := list2
	for t.Next != nil {
		t = t.Next
	}
	t.Next = q
	p.Next = list2
	return list1
}
```

### **...**

```

```

<!-- tabs:end -->
