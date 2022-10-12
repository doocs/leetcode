# [817. 链表组件](https://leetcode.cn/problems/linked-list-components)

[English Version](/solution/0800-0899/0817.Linked%20List%20Components/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定链表头结点&nbsp;<code>head</code>，该链表上的每个结点都有一个 <strong>唯一的整型值</strong> 。同时给定列表&nbsp;<code>nums</code>，该列表是上述链表中整型值的一个子集。</p>

<p>返回列表&nbsp;<code>nums</code>&nbsp;中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表&nbsp;<code>nums</code>&nbsp;中）构成的集合。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0817.Linked%20List%20Components/images/lc-linkedlistcom1.jpg" /></p>

<pre>
<strong>输入:</strong> head = [0,1,2,3], nums = [0,1,3]
<strong>输出:</strong> 2
<strong>解释:</strong> 链表中,0 和 1 是相连接的，且 nums 中不包含 2，所以 [0, 1] 是 nums 的一个组件，同理 [3] 也是一个组件，故返回 2。</pre>

<p><strong>示例 2：</strong></p>

<p><strong>&nbsp;</strong><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0817.Linked%20List%20Components/images/lc-linkedlistcom2.jpg" /></p>

<pre>
<strong>输入:</strong> head = [0,1,2,3,4], nums = [0,3,1,4]
<strong>输出:</strong> 2
<strong>解释:</strong> 链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>链表中节点数为<code>n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= Node.val &lt; n</code></li>
	<li><code>Node.val</code>&nbsp;中所有值 <strong>不同</strong></li>
	<li><code>1 &lt;= nums.length &lt;= n</code></li>
	<li><code>0 &lt;= nums[i] &lt; n</code></li>
	<li><code>nums</code> 中所有值 <strong>不同</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 链表一次遍历**

题目中需要判断链表中节点的值是否在数组 `nums` 中，因此我们可以使用哈希表 $s$ 存储数组 `nums` 中的值。

然后遍历链表，找到第一个在哈希表 $s$ 中的节点，然后从该节点开始遍历，直到遇到不在哈希表 $s$ 中的节点，这样就找到了一个组件，然后继续遍历链表，直到遍历完整个链表。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为链表的节点个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def numComponents(self, head: Optional[ListNode], nums: List[int]) -> int:
        ans = 0
        s = set(nums)
        while head:
            while head and head.val not in s:
                head = head.next
            ans += head is not None
            while head and head.val in s:
                head = head.next
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    public int numComponents(ListNode head, int[] nums) {
        int ans = 0;
        Set<Integer> s = new HashSet<>();
        for (int v : nums) {
            s.add(v);
        }
        while (head != null) {
            while (head != null && !s.contains(head.val)) {
                head = head.next;
            }
            ans += head != null ? 1 : 0;
            while (head != null && s.contains(head.val)) {
                head = head.next;
            }
        }
        return ans;
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
    int numComponents(ListNode* head, vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        int ans = 0;
        while (head) {
            while (head && !s.count(head->val)) head = head->next;
            ans += head != nullptr;
            while (head && s.count(head->val)) head = head->next;
        }
        return ans;
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
func numComponents(head *ListNode, nums []int) int {
	s := map[int]bool{}
	for _, v := range nums {
		s[v] = true
	}
	ans := 0
	for head != nil {
		for head != nil && !s[head.Val] {
			head = head.Next
		}
		if head != nil {
			ans++
		}
		for head != nil && s[head.Val] {
			head = head.Next
		}
	}
	return ans
}
```

### **JavaScript**

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @param {number[]} nums
 * @return {number}
 */
var numComponents = function (head, nums) {
    const s = new Set(nums);
    let ans = 0;
    while (head) {
        while (head && !s.has(head.val)) {
            head = head.next;
        }
        ans += head != null;
        while (head && s.has(head.val)) {
            head = head.next;
        }
    }
    return ans;
};
```

### **TypeScript**

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

function numComponents(head: ListNode | null, nums: number[]): number {
    const set = new Set<number>(nums);
    let res = 0;
    let cur = head;
    let inSet = false;
    while (cur != null) {
        if (set.has(cur.val)) {
            if (!inSet) {
                inSet = true;
                res++;
            }
        } else {
            inSet = false;
        }
        cur = cur.next;
    }
    return res;
}
```

### **Rust**

```rust
// Definition for singly-linked list.
// #[derive(PartialEq, Eq, Clone, Debug)]
// pub struct ListNode {
//   pub val: i32,
//   pub next: Option<Box<ListNode>>
// }
//
// impl ListNode {
//   #[inline]
//   fn new(val: i32) -> Self {
//     ListNode {
//       next: None,
//       val
//     }
//   }
// }
use std::collections::HashSet;
impl Solution {
    pub fn num_components(head: Option<Box<ListNode>>, nums: Vec<i32>) -> i32 {
        let set = nums.into_iter().collect::<HashSet<i32>>();
        let mut res = 0;
        let mut in_set = false;
        let mut cur = &head;
        while let Some(node) = cur {
            if set.contains(&node.val) {
                if !in_set {
                    in_set = true;
                    res += 1;
                }
            } else {
                in_set = false;
            }
            cur = &node.next;
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
