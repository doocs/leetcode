---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0725.Split%20Linked%20List%20in%20Parts/README.md
tags:
    - 链表
---

<!-- problem:start -->

# [725. 分隔链表](https://leetcode.cn/problems/split-linked-list-in-parts)

[English Version](/solution/0700-0799/0725.Split%20Linked%20List%20in%20Parts/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个头结点为 <code>head</code> 的单链表和一个整数 <code>k</code> ，请你设计一个算法将链表分隔为 <code>k</code> 个连续的部分。</p>

<p>每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null 。</p>

<p>这 <code>k</code> 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该大于或等于排在后面的长度。</p>

<p>返回一个由上述 <code>k</code> 部分组成的数组。</p>
&nbsp;

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0725.Split%20Linked%20List%20in%20Parts/images/split1-lc.jpg" style="width: 400px; height: 134px;" />
<pre>
<strong>输入：</strong>head = [1,2,3], k = 5
<strong>输出：</strong>[[1],[2],[3],[],[]]
<strong>解释：</strong>
第一个元素 output[0] 为 output[0].val = 1 ，output[0].next = null 。
最后一个元素 output[4] 为 null ，但它作为 ListNode 的字符串表示是 [] 。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0725.Split%20Linked%20List%20in%20Parts/images/split2-lc.jpg" style="width: 600px; height: 60px;" />
<pre>
<strong>输入：</strong>head = [1,2,3,4,5,6,7,8,9,10], k = 3
<strong>输出：</strong>[[1,2,3,4],[5,6,7],[8,9,10]]
<strong>解释：</strong>
输入被分成了几个连续的部分，并且每部分的长度相差不超过 1 。前面部分的长度大于等于后面部分的长度。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>链表中节点的数目在范围 <code>[0, 1000]</code></li>
	<li><code>0 &lt;= Node.val &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= 50</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们先遍历链表，得到链表的长度 $n$，然后我们计算出平均长度 $\textit{cnt} = \lfloor \frac{n}{k} \rfloor$ 和余数 $\textit{mod} = n \bmod k$。那么对于前 $\textit{mod}$ 个部分，每个部分的长度为 $\textit{cnt} + 1$，其余部分的长度为 $\textit{cnt}$。

接下来，我们只需要遍历链表，将链表分割成 $k$ 个部分即可。

时间复杂度 $O(n)$，空间复杂度 $O(k)$。其中 $n$ 为链表的长度。

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
