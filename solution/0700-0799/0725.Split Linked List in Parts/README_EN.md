---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0725.Split%20Linked%20List%20in%20Parts/README_EN.md
tags:
    - Linked List
---

<!-- problem:start -->

# [725. Split Linked List in Parts](https://leetcode.com/problems/split-linked-list-in-parts)

[中文文档](/solution/0700-0799/0725.Split%20Linked%20List%20in%20Parts/README.md)

## Description

<!-- description:start -->

<p>Given the <code>head</code> of a singly linked list and an integer <code>k</code>, split the linked list into <code>k</code> consecutive linked list parts.</p>

<p>The length of each part should be as equal as possible: no two parts should have a size differing by more than one. This may lead to some parts being null.</p>

<p>The parts should be in the order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal to parts occurring later.</p>

<p>Return <em>an array of the </em><code>k</code><em> parts</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0725.Split%20Linked%20List%20in%20Parts/images/split1-lc.jpg" style="width: 400px; height: 134px;" />
<pre>
<strong>Input:</strong> head = [1,2,3], k = 5
<strong>Output:</strong> [[1],[2],[3],[],[]]
<strong>Explanation:</strong>
The first element output[0] has output[0].val = 1, output[0].next = null.
The last element output[4] is null, but its string representation as a ListNode is [].
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0725.Split%20Linked%20List%20in%20Parts/images/split2-lc.jpg" style="width: 600px; height: 60px;" />
<pre>
<strong>Input:</strong> head = [1,2,3,4,5,6,7,8,9,10], k = 3
<strong>Output:</strong> [[1,2,3,4],[5,6,7],[8,9,10]]
<strong>Explanation:</strong>
The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is in the range <code>[0, 1000]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= 50</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

First, we traverse the linked list to obtain its length $n$, and then we calculate the average length $\textit{cnt} = \lfloor \frac{n}{k} \rfloor$ and the remainder $\textit{mod} = n \bmod k$. For the first $\textit{mod}$ parts, each part has a length of $\textit{cnt} + 1$, while the lengths of the remaining parts are $\textit{cnt}$.

Next, we just need to traverse the linked list and split it into $k$ parts.

The time complexity is $O(n)$, and the space complexity is $O(k)$. Here, $n$ is the length of the linked list.

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def splitListToParts(
        self, head: Optional[ListNode], k: int
    ) -> List[Optional[ListNode]]:
        n = 0
        cur = head
        while cur:
            n += 1
            cur = cur.next
        cnt, mod = divmod(n, k)
        ans = [None] * k
        cur = head
        for i in range(k):
            if cur is None:
                break
            ans[i] = cur
            m = cnt + int(i < mod)
            for _ in range(1, m):
                cur = cur.next
            nxt = cur.next
            cur.next = None
            cur = nxt
        return ans
```

#### Java

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
    public ListNode[] splitListToParts(ListNode head, int k) {
        int n = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            ++n;
        }
        int cnt = n / k, mod = n % k;
        ListNode[] ans = new ListNode[k];
        ListNode cur = head;
        for (int i = 0; i < k && cur != null; ++i) {
            ans[i] = cur;
            int m = cnt + (i < mod ? 1 : 0);
            for (int j = 1; j < m; ++j) {
                cur = cur.next;
            }
            ListNode nxt = cur.next;
            cur.next = null;
            cur = nxt;
        }
        return ans;
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
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    vector<ListNode*> splitListToParts(ListNode* head, int k) {
        int n = 0;
        for (ListNode* cur = head; cur != nullptr; cur = cur->next) {
            ++n;
        }
        int cnt = n / k, mod = n % k;
        vector<ListNode*> ans(k, nullptr);
        ListNode* cur = head;
        for (int i = 0; i < k && cur != nullptr; ++i) {
            ans[i] = cur;
            int m = cnt + (i < mod ? 1 : 0);
            for (int j = 1; j < m; ++j) {
                cur = cur->next;
            }
            ListNode* nxt = cur->next;
            cur->next = nullptr;
            cur = nxt;
        }
        return ans;
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
func splitListToParts(head *ListNode, k int) []*ListNode {
	n := 0
	for cur := head; cur != nil; cur = cur.Next {
		n++
	}

	cnt := n / k
	mod := n % k
	ans := make([]*ListNode, k)
	cur := head

	for i := 0; i < k && cur != nil; i++ {
		ans[i] = cur
		m := cnt
		if i < mod {
			m++
		}
		for j := 1; j < m; j++ {
			cur = cur.Next
		}
		next := cur.Next
		cur.Next = nil
		cur = next
	}

	return ans
}
```

#### TypeScript

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

function splitListToParts(head: ListNode | null, k: number): Array<ListNode | null> {
    let n = 0;
    for (let cur = head; cur !== null; cur = cur.next) {
        n++;
    }
    const cnt = (n / k) | 0;
    const mod = n % k;
    const ans: Array<ListNode | null> = Array(k).fill(null);
    let cur = head;
    for (let i = 0; i < k && cur !== null; i++) {
        ans[i] = cur;
        let m = cnt + (i < mod ? 1 : 0);
        for (let j = 1; j < m; j++) {
            cur = cur.next!;
        }
        let next = cur.next;
        cur.next = null;
        cur = next;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
