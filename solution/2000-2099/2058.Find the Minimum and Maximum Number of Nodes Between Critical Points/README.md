---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2058.Find%20the%20Minimum%20and%20Maximum%20Number%20of%20Nodes%20Between%20Critical%20Points/README.md
rating: 1310
source: 第 265 场周赛 Q2
tags:
    - 链表
---

<!-- problem:start -->

# [2058. 找出临界点之间的最小和最大距离](https://leetcode.cn/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points)

[English Version](/solution/2000-2099/2058.Find%20the%20Minimum%20and%20Maximum%20Number%20of%20Nodes%20Between%20Critical%20Points/README_EN.md)

## 题目描述

<!-- description:start -->

<p>链表中的 <strong>临界点</strong> 定义为一个 <strong>局部极大值点</strong> <strong>或</strong> <strong>局部极小值点 。</strong></p>

<p>如果当前节点的值 <strong>严格大于</strong> 前一个节点和后一个节点，那么这个节点就是一个<strong>&nbsp; 局部极大值点</strong> 。</p>

<p>如果当前节点的值 <strong>严格小于</strong> 前一个节点和后一个节点，那么这个节点就是一个<strong>&nbsp; 局部极小值点</strong> 。</p>

<p>注意：节点只有在同时存在前一个节点和后一个节点的情况下，才能成为一个 <strong>局部极大值点 / 极小值点</strong> 。</p>

<p>给你一个链表 <code>head</code> ，返回一个长度为 2 的数组<em> </em><code>[minDistance, maxDistance]</code> ，其中<em> </em><code>minDistance</code><em> </em>是任意两个不同临界点之间的最小距离，<code>maxDistance</code> 是任意两个不同临界点之间的最大距离。如果临界点少于两个，则返回 <code>[-1，-1]</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2058.Find%20the%20Minimum%20and%20Maximum%20Number%20of%20Nodes%20Between%20Critical%20Points/images/a1.png" style="width: 148px; height: 55px;" /></p>

<pre>
<strong>输入：</strong>head = [3,1]
<strong>输出：</strong>[-1,-1]
<strong>解释：</strong>链表 [3,1] 中不存在临界点。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2058.Find%20the%20Minimum%20and%20Maximum%20Number%20of%20Nodes%20Between%20Critical%20Points/images/a2.png" style="width: 624px; height: 46px;" /></p>

<pre>
<strong>输入：</strong>head = [5,3,1,2,5,1,2]
<strong>输出：</strong>[1,3]
<strong>解释：</strong>存在三个临界点：
- [5,3,<em><strong>1</strong></em>,2,5,1,2]：第三个节点是一个局部极小值点，因为 1 比 3 和 2 小。
- [5,3,1,2,<em><strong>5</strong></em>,1,2]：第五个节点是一个局部极大值点，因为 5 比 2 和 1 大。
- [5,3,1,2,5,<em><strong>1</strong></em>,2]：第六个节点是一个局部极小值点，因为 1 比 5 和 2 小。
第五个节点和第六个节点之间距离最小。minDistance = 6 - 5 = 1 。
第三个节点和第六个节点之间距离最大。maxDistance = 6 - 3 = 3 。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2058.Find%20the%20Minimum%20and%20Maximum%20Number%20of%20Nodes%20Between%20Critical%20Points/images/a5.png" style="width: 624px; height: 39px;" /></p>

<pre>
<strong>输入：</strong>head = [1,3,2,2,3,2,2,2,7]
<strong>输出：</strong>[3,3]
<strong>解释：</strong>存在两个临界点：
- [1,<em><strong>3</strong></em>,2,2,3,2,2,2,7]：第二个节点是一个局部极大值点，因为 3 比 1 和 2 大。
- [1,3,2,2,<em><strong>3</strong></em>,2,2,2,7]：第五个节点是一个局部极大值点，因为 3 比 2 和 2 大。
最小和最大距离都存在于第二个节点和第五个节点之间。
因此，minDistance 和 maxDistance 是 5 - 2 = 3 。
注意，最后一个节点不算一个局部极大值点，因为它之后就没有节点了。
</pre>

<p><strong>示例 4：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2058.Find%20the%20Minimum%20and%20Maximum%20Number%20of%20Nodes%20Between%20Critical%20Points/images/a4.png" style="width: 345px; height: 52px;" /></p>

<pre>
<strong>输入：</strong>head = [2,3,3,2]
<strong>输出：</strong>[-1,-1]
<strong>解释：</strong>链表 [2,3,3,2] 中不存在临界点。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>链表中节点的数量在范围 <code>[2, 10<sup>5</sup>]</code> 内</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：直接遍历

根据题目描述，我们需要找出链表的第一个临界点和最后一个临界点位置 $\textit{first}$ 和 $\textit{last}$，这样可以计算出最大距离 $\textit{maxDistance} = \textit{last} - \textit{first}$。对于最小距离 $\textit{minDistance}$，我们需要遍历链表，计算相邻两个临界点之间的距离，取最小值即可。

时间复杂度 $O(n)$，其中 $n$ 是链表的长度。空间复杂度 $O(1)$。

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
