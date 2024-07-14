---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3217.Delete%20Nodes%20From%20Linked%20List%20Present%20in%20Array/README_EN.md
---

<!-- problem:start -->

# [3217. Delete Nodes From Linked List Present in Array](https://leetcode.com/problems/delete-nodes-from-linked-list-present-in-array)

[中文文档](/solution/3200-3299/3217.Delete%20Nodes%20From%20Linked%20List%20Present%20in%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an array of integers <code>nums</code> and the <code>head</code> of a linked list. Return the <code>head</code> of the modified linked list after <strong>removing</strong> all nodes from the linked list that have a value that exists in <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], head = [1,2,3,4,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">[4,5]</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3217.Delete%20Nodes%20From%20Linked%20List%20Present%20in%20Array/images/linkedlistexample0.png" style="width: 400px; height: 66px;" /></strong></p>

<p>Remove the nodes with values 1, 2, and 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1], head = [1,2,1,2,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,2,2]</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3217.Delete%20Nodes%20From%20Linked%20List%20Present%20in%20Array/images/linkedlistexample1.png" style="height: 62px; width: 450px;" /></p>

<p>Remove the nodes with value 1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5], head = [1,2,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,3,4]</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3217.Delete%20Nodes%20From%20Linked%20List%20Present%20in%20Array/images/linkedlistexample2.png" style="width: 400px; height: 83px;" /></strong></p>

<p>No node has value 5.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li>All elements in <code>nums</code> are unique.</li>
	<li>The number of nodes in the given list is in the range <code>[1, 10<sup>5</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li>The input is generated such that there is at least one node in the linked list that has a value not present in <code>nums</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We can use a hash table $\textit{s}$ to store all the elements in the array $\textit{nums}$. Then, we define a dummy node $\textit{dummy}$ and point it to the head node of the list $\textit{head}$.

Next, we traverse the list starting from the dummy node $\textit{dummy}$. If the value of the next node of the current node is in the hash table $\textit{s}$, we make the current node point to the next next node; otherwise, we move the current node pointer to the next node.

Finally, we return the next node of the dummy node $\textit{dummy}$.

The time complexity is $O(n + m)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$, and $m$ is the length of the list $\textit{head}$.

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def modifiedList(
        self, nums: List[int], head: Optional[ListNode]
    ) -> Optional[ListNode]:
        s = set(nums)
        pre = dummy = ListNode(next=head)
        while pre.next:
            if pre.next.val in s:
                pre.next = pre.next.next
            else:
                pre = pre.next
        return dummy.next
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
    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> s = new HashSet<>();
        for (int x : nums) {
            s.add(x);
        }
        ListNode dummy = new ListNode(0, head);
        for (ListNode pre = dummy; pre.next != null;) {
            if (s.contains(pre.next.val)) {
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }
        return dummy.next;
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
    ListNode* modifiedList(vector<int>& nums, ListNode* head) {
        unordered_set<int> s(nums.begin(), nums.end());
        ListNode* dummy = new ListNode(0, head);
        for (ListNode* pre = dummy; pre->next;) {
            if (s.count(pre->next->val)) {
                pre->next = pre->next->next;
            } else {
                pre = pre->next;
            }
        }
        return dummy->next;
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
func modifiedList(nums []int, head *ListNode) *ListNode {
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	dummy := &ListNode{Next: head}
	for pre := dummy; pre.Next != nil; {
		if s[pre.Next.Val] {
			pre.Next = pre.Next.Next
		} else {
			pre = pre.Next
		}
	}
	return dummy.Next
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

function modifiedList(nums: number[], head: ListNode | null): ListNode | null {
    const s: Set<number> = new Set(nums);
    const dummy = new ListNode(0, head);
    for (let pre = dummy; pre.next; ) {
        if (s.has(pre.next.val)) {
            pre.next = pre.next.next;
        } else {
            pre = pre.next;
        }
    }
    return dummy.next;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
