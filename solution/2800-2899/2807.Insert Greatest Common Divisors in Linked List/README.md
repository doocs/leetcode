# [2807. 在链表中插入最大公约数](https://leetcode.cn/problems/insert-greatest-common-divisors-in-linked-list)

[English Version](/solution/2800-2899/2807.Insert%20Greatest%20Common%20Divisors%20in%20Linked%20List/README_EN.md)

<!-- tags:链表,数学,数论 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个链表的头&nbsp;<code>head</code>&nbsp;，每个结点包含一个整数值。</p>

<p>在相邻结点之间，请你插入一个新的结点，结点值为这两个相邻结点值的 <strong>最大公约数</strong>&nbsp;。</p>

<p>请你返回插入之后的链表。</p>

<p>两个数的 <strong>最大公约数</strong>&nbsp;是可以被两个数字整除的最大正整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2807.Insert%20Greatest%20Common%20Divisors%20in%20Linked%20List/images/ex1_copy.png" style="width: 641px; height: 181px;"></p>

<pre><b>输入：</b>head = [18,6,10,3]
<b>输出：</b>[18,6,6,2,10,1,3]
<b>解释：</b>第一幅图是一开始的链表，第二幅图是插入新结点后的图（蓝色结点为新插入结点）。
- 18 和 6 的最大公约数为 6 ，插入第一和第二个结点之间。
- 6 和 10 的最大公约数为 2 ，插入第二和第三个结点之间。
- 10 和 3 的最大公约数为 1 ，插入第三和第四个结点之间。
所有相邻结点之间都插入完毕，返回链表。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2807.Insert%20Greatest%20Common%20Divisors%20in%20Linked%20List/images/ex2_copy1.png" style="width: 51px; height: 191px;"></p>

<pre><b>输入：</b>head = [7]
<strong>输出：</strong>[7]
<b>解释：</b>第一幅图是一开始的链表，第二幅图是插入新结点后的图（蓝色结点为新插入结点）。
没有相邻结点，所以返回初始链表。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>链表中结点数目在&nbsp;<code>[1, 5000]</code> 之间。</li>
	<li><code>1 &lt;= Node.val &lt;= 1000</code></li>
</ul>

## 解法

### 方法一：模拟

我们用两个指针 $pre$ 和 $cur$ 分别指向当前遍历到的结点和下一个结点，那么我们只需要在 $pre$ 和 $cur$ 之间插入一个新的结点即可。因此，每次计算出 $pre$ 和 $cur$ 的最大公约数 $x$，然后在 $pre$ 和 $cur$ 之间插入一个值为 $x$ 的新结点，然后更新 $pre = cur$，并且 $cur = cur.next$，继续遍历链表，直到 $cur$ 为空。

时间复杂度 $O(n \times \log M)$，其中 $n$ 是链表的长度，而 $M$ 是链表中结点的最大值。忽略结果链表的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def insertGreatestCommonDivisors(
        self, head: Optional[ListNode]
    ) -> Optional[ListNode]:
        pre, cur = head, head.next
        while cur:
            x = gcd(pre.val, cur.val)
            pre.next = ListNode(x, cur)
            pre, cur = cur, cur.next
        return head
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
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        for (ListNode pre = head, cur = head.next; cur != null; cur = cur.next) {
            int x = gcd(pre.val, cur.val);
            pre.next = new ListNode(x, cur);
            pre = cur;
        }
        return head;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
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
    ListNode* insertGreatestCommonDivisors(ListNode* head) {
        ListNode* pre = head;
        for (ListNode* cur = head->next; cur; cur = cur->next) {
            int x = gcd(pre->val, cur->val);
            pre->next = new ListNode(x, cur);
            pre = cur;
        }
        return head;
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
func insertGreatestCommonDivisors(head *ListNode) *ListNode {
	for pre, cur := head, head.Next; cur != nil; cur = cur.Next {
		x := gcd(pre.Val, cur.Val)
		pre.Next = &ListNode{x, cur}
		pre = cur
	}
	return head
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
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

function insertGreatestCommonDivisors(head: ListNode | null): ListNode | null {
    for (let pre = head, cur = head.next; cur; cur = cur.next) {
        const x = gcd(pre.val, cur.val);
        pre.next = new ListNode(x, cur);
        pre = cur;
    }
    return head;
}

function gcd(a: number, b: number): number {
    if (b === 0) {
        return a;
    }
    return gcd(b, a % b);
}
```

<!-- tabs:end -->

<!-- end -->
