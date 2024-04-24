# [1836. 从未排序的链表中移除重复元素 🔒](https://leetcode.cn/problems/remove-duplicates-from-an-unsorted-linked-list)

[English Version](/solution/1800-1899/1836.Remove%20Duplicates%20From%20an%20Unsorted%20Linked%20List/README_EN.md)

<!-- tags:哈希表,链表 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个链表的第一个节点 <code>head</code> ，找到链表中所有出现<strong>多于一次</strong>的元素，并删除这些元素所在的节点。</p>

<p>返回删除后的链表。</p>

<p> </p>

<p><strong>示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1836.Remove%20Duplicates%20From%20an%20Unsorted%20Linked%20List/images/tmp-linked-list.jpg" style="width: 422px; height: 222px;">
<pre><strong>输入:</strong> head = [1,2,3,2]
<strong>输出:</strong> [1,3]
<strong>解释:</strong> 2 在链表中出现了两次，所以所有的 2 都需要被删除。删除了所有的 2 之后，我们还剩下 [1,3] 。
</pre>

<p><strong>示例 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1836.Remove%20Duplicates%20From%20an%20Unsorted%20Linked%20List/images/tmp-linked-list-1.jpg" style="width: 422px; height: 151px;">
<pre><strong>输入:</strong> head = [2,1,1,2]
<strong>输出:</strong> []
<strong>解释:</strong> 2 和 1 都出现了两次。所有元素都需要被删除。
</pre>

<p><strong>示例 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1836.Remove%20Duplicates%20From%20an%20Unsorted%20Linked%20List/images/tmp-linked-list-2.jpg" style="width: 500px; height: 142px;">
<pre><strong>输入:</strong> head = [3,2,2,1,3,2,4]
<strong>输出:</strong> [1,4]
<strong>解释: </strong>3 出现了两次，且 2 出现了三次。移除了所有的 3 和 2 后，我们还剩下 [1,4] 。
</pre>

<p> </p>

<p><b>提示：</b></p>

<ul>
	<li>链表中节点个数的范围是 <code>[1, 10<sup>5</sup>]</code></li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：哈希表

我们可以用哈希表 $cnt$ 统计链表中每个元素出现的次数，然后遍历链表，删除出现次数大于 1 的元素。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为链表的长度。

<!-- tabs:start -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def deleteDuplicatesUnsorted(self, head: ListNode) -> ListNode:
        cnt = Counter()
        cur = head
        while cur:
            cnt[cur.val] += 1
            cur = cur.next
        dummy = ListNode(0, head)
        pre, cur = dummy, head
        while cur:
            if cnt[cur.val] > 1:
                pre.next = cur.next
            else:
                pre = cur
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
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (ListNode cur = head; cur != null; cur = cur.next) {
            cnt.put(cur.val, cnt.getOrDefault(cur.val, 0) + 1);
        }
        ListNode dummy = new ListNode(0, head);
        for (ListNode pre = dummy, cur = head; cur != null; cur = cur.next) {
            if (cnt.get(cur.val) > 1) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
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
    ListNode* deleteDuplicatesUnsorted(ListNode* head) {
        unordered_map<int, int> cnt;
        for (ListNode* cur = head; cur; cur = cur->next) {
            cnt[cur->val]++;
        }
        ListNode* dummy = new ListNode(0, head);
        for (ListNode *pre = dummy, *cur = head; cur; cur = cur->next) {
            if (cnt[cur->val] > 1) {
                pre->next = cur->next;
            } else {
                pre = cur;
            }
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
func deleteDuplicatesUnsorted(head *ListNode) *ListNode {
	cnt := map[int]int{}
	for cur := head; cur != nil; cur = cur.Next {
		cnt[cur.Val]++
	}
	dummy := &ListNode{0, head}
	for pre, cur := dummy, head; cur != nil; cur = cur.Next {
		if cnt[cur.Val] > 1 {
			pre.Next = cur.Next
		} else {
			pre = cur
		}
	}
	return dummy.Next
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

function deleteDuplicatesUnsorted(head: ListNode | null): ListNode | null {
    const cnt: Map<number, number> = new Map();
    for (let cur = head; cur; cur = cur.next) {
        const x = cur.val;
        cnt.set(x, (cnt.get(x) ?? 0) + 1);
    }
    const dummy = new ListNode(0, head);
    for (let pre = dummy, cur = head; cur; cur = cur.next) {
        if (cnt.get(cur.val)! > 1) {
            pre.next = cur.next;
        } else {
            pre = cur;
        }
    }
    return dummy.next;
}
```

<!-- tabs:end -->

<!-- end -->
