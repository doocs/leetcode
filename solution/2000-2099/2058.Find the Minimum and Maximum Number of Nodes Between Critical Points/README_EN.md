---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2058.Find%20the%20Minimum%20and%20Maximum%20Number%20of%20Nodes%20Between%20Critical%20Points/README_EN.md
rating: 1310
source: Weekly Contest 265 Q2
tags:
    - Linked List
---

<!-- problem:start -->

# [2058. Find the Minimum and Maximum Number of Nodes Between Critical Points](https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points)

[中文文档](/solution/2000-2099/2058.Find%20the%20Minimum%20and%20Maximum%20Number%20of%20Nodes%20Between%20Critical%20Points/README.md)

## Description

<!-- description:start -->

<p>A <strong>critical point</strong> in a linked list is defined as <strong>either</strong> a <strong>local maxima</strong> or a <strong>local minima</strong>.</p>

<p>A node is a <strong>local maxima</strong> if the current node has a value <strong>strictly greater</strong> than the previous node and the next node.</p>

<p>A node is a <strong>local minima</strong> if the current node has a value <strong>strictly smaller</strong> than the previous node and the next node.</p>

<p>Note that a node can only be a local maxima/minima if there exists <strong>both</strong> a previous node and a next node.</p>

<p>Given a linked list <code>head</code>, return <em>an array of length 2 containing </em><code>[minDistance, maxDistance]</code><em> where </em><code>minDistance</code><em> is the <strong>minimum distance</strong> between <strong>any&nbsp;two distinct</strong> critical points and </em><code>maxDistance</code><em> is the <strong>maximum distance</strong> between <strong>any&nbsp;two distinct</strong> critical points. If there are <strong>fewer</strong> than two critical points, return </em><code>[-1, -1]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2058.Find%20the%20Minimum%20and%20Maximum%20Number%20of%20Nodes%20Between%20Critical%20Points/images/a1.png" style="width: 148px; height: 55px;" />
<pre>
<strong>Input:</strong> head = [3,1]
<strong>Output:</strong> [-1,-1]
<strong>Explanation:</strong> There are no critical points in [3,1].
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2058.Find%20the%20Minimum%20and%20Maximum%20Number%20of%20Nodes%20Between%20Critical%20Points/images/a2.png" style="width: 624px; height: 46px;" />
<pre>
<strong>Input:</strong> head = [5,3,1,2,5,1,2]
<strong>Output:</strong> [1,3]
<strong>Explanation:</strong> There are three critical points:
- [5,3,<strong><u>1</u></strong>,2,5,1,2]: The third node is a local minima because 1 is less than 3 and 2.
- [5,3,1,2,<u><strong>5</strong></u>,1,2]: The fifth node is a local maxima because 5 is greater than 2 and 1.
- [5,3,1,2,5,<u><strong>1</strong></u>,2]: The sixth node is a local minima because 1 is less than 5 and 2.
The minimum distance is between the fifth and the sixth node. minDistance = 6 - 5 = 1.
The maximum distance is between the third and the sixth node. maxDistance = 6 - 3 = 3.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2058.Find%20the%20Minimum%20and%20Maximum%20Number%20of%20Nodes%20Between%20Critical%20Points/images/a5.png" style="width: 624px; height: 39px;" />
<pre>
<strong>Input:</strong> head = [1,3,2,2,3,2,2,2,7]
<strong>Output:</strong> [3,3]
<strong>Explanation:</strong> There are two critical points:
- [1,<u><strong>3</strong></u>,2,2,3,2,2,2,7]: The second node is a local maxima because 3 is greater than 1 and 2.
- [1,3,2,2,<u><strong>3</strong></u>,2,2,2,7]: The fifth node is a local maxima because 3 is greater than 2 and 2.
Both the minimum and maximum distances are between the second and the fifth node.
Thus, minDistance and maxDistance is 5 - 2 = 3.
Note that the last node is not considered a local maxima because it does not have a next node.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is in the range <code>[2, 10<sup>5</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Direct Traversal

Based on the problem description, we need to find the positions of the first and last critical points in the linked list, $\textit{first}$ and $\textit{last}$, respectively. This allows us to calculate the maximum distance $\textit{maxDistance} = \textit{last} - \textit{first}$. For the minimum distance $\textit{minDistance}$, we need to traverse the linked list, calculate the distance between two adjacent critical points, and take the minimum value.

The time complexity is $O(n)$, where $n$ is the length of the linked list. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def nodesBetweenCriticalPoints(self, head: Optional[ListNode]) -> List[int]:
        ans = [inf, -inf]
        first = last = -1
        i = 0
        while head.next.next:
            a, b, c = head.val, head.next.val, head.next.next.val
            if a > b < c or a < b > c:
                if last == -1:
                    first = last = i
                else:
                    ans[0] = min(ans[0], i - last)
                    last = i
                    ans[1] = max(ans[1], last - first)
            i += 1
            head = head.next
        return [-1, -1] if first == last else ans
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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] ans = {1 << 30, 0};
        int first = -1, last = -1;
        for (int i = 0; head.next.next != null; head = head.next, ++i) {
            int a = head.val, b = head.next.val, c = head.next.next.val;
            if (b < Math.min(a, c) || b > Math.max(a, c)) {
                if (last == -1) {
                    first = i;
                    last = i;
                } else {
                    ans[0] = Math.min(ans[0], i - last);
                    last = i;
                    ans[1] = Math.max(ans[1], last - first);
                }
            }
        }
        return first == last ? new int[] {-1, -1} : ans;
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
    vector<int> nodesBetweenCriticalPoints(ListNode* head) {
        vector<int> ans = {1 << 30, 0};
        int first = -1, last = -1;
        for (int i = 0; head->next->next; head = head->next, ++i) {
            int a = head->val, b = head->next->val, c = head->next->next->val;
            if (b < min(a, c) || b > max(a, c)) {
                if (last == -1) {
                    first = i;
                    last = i;
                } else {
                    ans[0] = min(ans[0], i - last);
                    last = i;
                    ans[1] = max(ans[1], last - first);
                }
            }
        }
        return first == last ? vector<int>{-1, -1} : ans;
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
func nodesBetweenCriticalPoints(head *ListNode) []int {
	ans := []int{1 << 30, 0}
	first, last := -1, -1
	for i := 0; head.Next.Next != nil; head, i = head.Next, i+1 {
		a, b, c := head.Val, head.Next.Val, head.Next.Next.Val
		if b < min(a, c) || b > max(a, c) {
			if last == -1 {
				first, last = i, i
			} else {
				ans[0] = min(ans[0], i-last)
				last = i
				ans[1] = max(ans[1], last-first)
			}
		}
	}
	if first == last {
		return []int{-1, -1}
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

function nodesBetweenCriticalPoints(head: ListNode | null): number[] {
    const ans: number[] = [Infinity, 0];
    let [first, last] = [-1, -1];
    for (let i = 0; head.next.next; head = head.next, ++i) {
        const [a, b, c] = [head.val, head.next.val, head.next.next.val];
        if (b < Math.min(a, c) || b > Math.max(a, c)) {
            if (last < 0) {
                first = i;
                last = i;
            } else {
                ans[0] = Math.min(ans[0], i - last);
                last = i;
                ans[1] = Math.max(ans[1], last - first);
            }
        }
    }
    return first === last ? [-1, -1] : ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
