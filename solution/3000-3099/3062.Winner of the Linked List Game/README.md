# [3062. 链表游戏的获胜者 🔒](https://leetcode.cn/problems/winner-of-the-linked-list-game)

[English Version](/solution/3000-3099/3062.Winner%20of%20the%20Linked%20List%20Game/README_EN.md)

<!-- tags:链表 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定长度为 <strong>偶数</strong>&nbsp;，包含整数的链表的&nbsp;<code>head</code>&nbsp;节点。</p>

<p>每个 <strong>奇数编号</strong> 的节点包含一个奇数，并且每个 <strong>偶数编号</strong> 的节点包含一个偶数。</p>

<p>我们把每个偶数编号的节点和它的下一个节点叫做一个 <strong>对</strong>，例如编号为&nbsp;<code>0</code>&nbsp;和&nbsp;<code>1</code>&nbsp;的节点是一对，编号为 <font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size: 12.6px; background-color: rgb(249, 242, 244);">2</span></font>&nbsp;和 <font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size: 12.6px; background-color: rgb(249, 242, 244);">3</span></font>&nbsp;的节点是一对，以此类推。</p>

<p>对于每个 <strong>对</strong>，我们比较对中节点的值：</p>

<ul>
	<li>如果奇数节点更大，<code>"Odd"</code>&nbsp;队得一分。</li>
	<li>如果偶数节点更大，<font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size: 12.6px; background-color: rgb(249, 242, 244);">"Even"</span></font>&nbsp;队得一分。</li>
</ul>

<p>返回分数更 <strong>高</strong> 的队名，如果分数相同，返回&nbsp;<code>"Tie"</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>head = [2,1]
<strong>输出：</strong>"Even"
<strong>解释：</strong>链表中只有一个对 (2,1)。因为 2 &gt; 1，偶数队得分。
因此，答案是 "Even"。
</pre>

<p>&nbsp;</p>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>head = [2,5,4,7,20,5] 
<strong>输出：</strong>"Odd" 
<strong>解释：</strong>此链表中有 3 对。让我们分别对每一对进行分析： 
(2,5) -&gt; 因为 2 &lt; 5，奇数队得分。
(4,7) -&gt; 因为 4 &lt; 7，奇数队得分。 
(20,5) -&gt; 因为 20 &gt; 5，偶数队得分。 
奇数队得 2 分，偶数队得 1 分，奇数队得分更高。 
因此，答案是 "Odd"。
</pre>

<p>&nbsp;</p>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>head = [4,5,2,1]
<strong>输出：</strong>"Tie"
<strong>解释：</strong>此链表中有 2 对。让我们分别对每一对进行分析：
(4,5) -&gt; 因为 4 &lt; 5，奇数队得分。
(2,1) -&gt; 因为 2 &gt; 1，偶数队得分。
每队得 1 分。
因此，答案是 "Tie"。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>链表中节点的数字在范围&nbsp;<code>[2, 100]</code>&nbsp;内。</li>
	<li>链表中的节点数为偶数。</li>
	<li><code>1 &lt;= Node.val &lt;= 100</code></li>
	<li>每个奇数编号节点的值都是奇数。</li>
	<li>每个偶数编号节点的值都是偶数。</li>
</ul>

## 解法

### 方法一：模拟

遍历链表，每次取出两个节点，比较它们的值，然后根据比较结果更新奇数和偶数的得分。最后比较奇数和偶数的得分，返回结果。

时间复杂度 $O(n)$，其中 $n$ 是链表的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def gameResult(self, head: Optional[ListNode]) -> str:
        odd = even = 0
        while head:
            a = head.val
            b = head.next.val
            odd += a < b
            even += a > b
            head = head.next.next
        if odd > even:
            return "Odd"
        if odd < even:
            return "Even"
        return "Tie"
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
    public String gameResult(ListNode head) {
        int odd = 0, even = 0;
        for (; head != null; head = head.next.next) {
            int a = head.val;
            int b = head.next.val;
            odd += a < b ? 1 : 0;
            even += a > b ? 1 : 0;
        }
        if (odd > even) {
            return "Odd";
        }
        if (odd < even) {
            return "Even";
        }
        return "Tie";
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
    string gameResult(ListNode* head) {
        int odd = 0, even = 0;
        for (; head != nullptr; head = head->next->next) {
            int a = head->val;
            int b = head->next->val;
            odd += a < b;
            even += a > b;
        }
        if (odd > even) {
            return "Odd";
        }
        if (odd < even) {
            return "Even";
        }
        return "Tie";
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
func gameResult(head *ListNode) string {
	var odd, even int
	for ; head != nil; head = head.Next.Next {
		a, b := head.Val, head.Next.Val
		if a < b {
			odd++
		}
		if a > b {
			even++
		}
	}
	if odd > even {
		return "Odd"
	}
	if odd < even {
		return "Even"
	}
	return "Tie"
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

function gameResult(head: ListNode | null): string {
    let [odd, even] = [0, 0];
    for (; head; head = head.next.next) {
        const [a, b] = [head.val, head.next.val];
        odd += a < b ? 1 : 0;
        even += a > b ? 1 : 0;
    }
    if (odd > even) {
        return 'Odd';
    }
    if (odd < even) {
        return 'Even';
    }
    return 'Tie';
}
```

<!-- tabs:end -->

<!-- end -->
