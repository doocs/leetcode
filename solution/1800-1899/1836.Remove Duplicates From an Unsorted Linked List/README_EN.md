# [1836. Remove Duplicates From an Unsorted Linked List](https://leetcode.com/problems/remove-duplicates-from-an-unsorted-linked-list)

[中文文档](/solution/1800-1899/1836.Remove%20Duplicates%20From%20an%20Unsorted%20Linked%20List/README.md)

## Description

<p>Given the <code>head</code> of a linked list, find all the values that appear <strong>more than once</strong> in the list and delete the nodes that have any of those values.</p>

<p>Return <em>the linked list after the deletions.</em></p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1836.Remove%20Duplicates%20From%20an%20Unsorted%20Linked%20List/images/tmp-linked-list.jpg" style="width: 422px; height: 222px;" />

<pre>

<strong>Input:</strong> head = [1,2,3,2]

<strong>Output:</strong> [1,3]

<strong>Explanation:</strong> 2 appears twice in the linked list, so all 2&#39;s should be deleted. After deleting all 2&#39;s, we are left with [1,3].

</pre>

<p><strong class="example">Example 2:</strong></p>

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1836.Remove%20Duplicates%20From%20an%20Unsorted%20Linked%20List/images/tmp-linked-list-1.jpg" style="width: 422px; height: 151px;" />

<pre>

<strong>Input:</strong> head = [2,1,1,2]

<strong>Output:</strong> []

<strong>Explanation:</strong> 2 and 1 both appear twice. All the elements should be deleted.

</pre>

<p><strong class="example">Example 3:</strong></p>

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1836.Remove%20Duplicates%20From%20an%20Unsorted%20Linked%20List/images/tmp-linked-list-2.jpg" style="width: 500px; height: 142px;" />

<pre>

<strong>Input:</strong> head = [3,2,2,1,3,2,4]

<strong>Output:</strong> [1,4]

<strong>Explanation: </strong>3 appears twice and 2 appears three times. After deleting all 3&#39;s and 2&#39;s, we are left with [1,4].

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li>The number of nodes in the list is in the range&nbsp;<code>[1, 10<sup>5</sup>]</code></li>
    <li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **Go**

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

### **...**

```

```

<!-- tabs:end -->
