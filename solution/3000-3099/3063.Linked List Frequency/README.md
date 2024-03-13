# [3063. 链表频率](https://leetcode.cn/problems/linked-list-frequency)

[English Version](/solution/3000-3099/3063.Linked%20List%20Frequency/README_EN.md)

<!-- tags:哈希表,链表,计数 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定包含 <code>k</code> 个&nbsp;<strong>不同&nbsp;</strong>元素的链表的&nbsp;<code>head</code>&nbsp;节点，创建一个长度为&nbsp;<code>k</code>&nbsp;的链表，包含给定链表中每个 <strong>不同元素</strong> 以 <strong>任何顺序</strong> 出现的 <span data-keyword="frequency-linkedlist">频率</span>&nbsp;。返回这个链表的头节点。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1: </strong></p>

<pre>
输入：head = [1,1,2,1,2,3]

输出：[3,2,1]

解释：列表中有 3 个不同的元素。1 的频率是 3，2 的频率是 2，3 的频率是 1。因此，我们返回 3 -&gt; 2 -&gt; 1。

注意 1 -&gt; 2 -&gt; 3，1 -&gt; 3 -&gt; 2，2 -&gt; 1 -&gt; 3，2 -&gt; 3 -&gt; 1，和 3 -&gt; 1 -&gt; 2 都是合法的答案。
</pre>

<p><strong class="example">示例 2: </strong></p>

<pre>
输入：head = [1,1,2,2,2]

输出：[2,3]

解释：列表中有 2 个不同的元素。1 和 2 出现的频率是 2 和 3。因此，我们返回 2 -&gt; 3。
</pre>

<p><strong class="example">示例 3: </strong></p>

<pre>
输入：head = [6,5,4,3,2,1]

输出：[1,1,1,1,1,1]

解释：列表中有 6 个不同的元素。每个元素的频率是 1。因此，我们返回 1 -&gt; 1 -&gt; 1 -&gt; 1 -&gt; 1 -&gt; 1。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>链表中的节点数字范围在&nbsp;<code>[1, 10<sup>5</sup>]</code>之间。</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：哈希表

我们用一个哈希表 $cnt$ 记录链表中每个元素值出现的次数，然后再遍历哈希表的值构造新的链表即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为链表的长度。

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
