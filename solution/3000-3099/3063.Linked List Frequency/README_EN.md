# [3063. Linked List Frequency](https://leetcode.com/problems/linked-list-frequency)

[中文文档](/solution/3000-3099/3063.Linked%20List%20Frequency/README.md)

<!-- tags: -->

## Description

<p>Given the <code>head</code> of a linked list containing <code>k</code> <strong>distinct</strong> elements, return <em>a linked list of length </em><code>k</code><em> containing the <span data-keyword="frequency-linkedlist">frequency</span> of each <strong>distinct</strong> element in the given linked list in <strong>any order</strong>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1: </strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> head = [1,1,1,2,2,3] </span></p>

<p><strong>Output: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> [3,2,1] </span></p>

<p><strong>Explanation: </strong> There are <code>3</code> distinct elements in the list. The frequency of <code>1</code> is <code>3</code>, the frequency of <code>2</code> is <code>2</code> and the frequency of <code>3</code> is <code>1</code>. Hence, we return <code>3 -&gt; 2 -&gt; 1</code>.</p>

<p>Note that <code>1 -&gt; 2 -&gt; 3</code>, <code>1 -&gt; 3 -&gt; 2</code>, <code>2 -&gt; 1 -&gt; 3</code>, <code>2 -&gt; 3 -&gt; 1</code>, and <code>3 -&gt; 1 -&gt; 2</code> are also valid answers.</p>
</div>

<p><strong class="example">Example 2: </strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> head = [1,1,2,2,2] </span></p>

<p><strong>Output: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> [2,3] </span></p>

<p><strong>Explanation: </strong> There are <code>2</code> distinct elements in the list. The frequency of <code>1</code> is <code>2</code> and the frequency of <code>2</code> is <code>3</code>. Hence, we return <code>2 -&gt; 3</code>.</p>
</div>

<p><strong class="example">Example 3: </strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> head = [6,5,4,3,2,1] </span></p>

<p><strong>Output: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> [1,1,1,1,1,1] </span></p>

<p><strong>Explanation: </strong> There are <code>6</code> distinct elements in the list. The frequency of each of them is <code>1</code>. Hence, we return <code>1 -&gt; 1 -&gt; 1 -&gt; 1 -&gt; 1 -&gt; 1</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is in the range <code>[1, 10<sup>5</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

### Solution 1: Hash Table

We use a hash table `cnt` to record the occurrence times of each element value in the linked list, then traverse the values of the hash table to construct a new linked list.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the linked list.

<!-- tabs:start -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def frequenciesOfElements(self, head: Optional[ListNode]) -> Optional[ListNode]:
        cnt = Counter()
        while head:
            cnt[head.val] += 1
            head = head.next
        dummy = ListNode()
        for val in cnt.values():
            dummy.next = ListNode(val, dummy.next)
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
    public ListNode frequenciesOfElements(ListNode head) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (; head != null; head = head.next) {
            cnt.merge(head.val, 1, Integer::sum);
        }
        ListNode dummy = new ListNode();
        for (int val : cnt.values()) {
            dummy.next = new ListNode(val, dummy.next);
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
    ListNode* frequenciesOfElements(ListNode* head) {
        unordered_map<int, int> cnt;
        for (; head; head = head->next) {
            cnt[head->val]++;
        }
        ListNode* dummy = new ListNode();
        for (auto& [_, val] : cnt) {
            dummy->next = new ListNode(val, dummy->next);
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
func frequenciesOfElements(head *ListNode) *ListNode {
	cnt := map[int]int{}
	for ; head != nil; head = head.Next {
		cnt[head.Val]++
	}
	dummy := &ListNode{}
	for _, val := range cnt {
		dummy.Next = &ListNode{val, dummy.Next}
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

function frequenciesOfElements(head: ListNode | null): ListNode | null {
    const cnt: Map<number, number> = new Map();
    for (; head; head = head.next) {
        cnt.set(head.val, (cnt.get(head.val) || 0) + 1);
    }
    const dummy = new ListNode();
    for (const val of cnt.values()) {
        dummy.next = new ListNode(val, dummy.next);
    }
    return dummy.next;
}
```

<!-- tabs:end -->

<!-- end -->
