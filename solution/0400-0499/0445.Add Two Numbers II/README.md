# [445. 两数相加 II](https://leetcode.cn/problems/add-two-numbers-ii)

[English Version](/solution/0400-0499/0445.Add%20Two%20Numbers%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个 <strong>非空 </strong>链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。</p>

<p>你可以假设除了数字 0 之外，这两个数字都不会以零开头。</p>

<p>&nbsp;</p>

<p><strong>示例1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0445.Add%20Two%20Numbers%20II/images/1626420025-fZfzMX-image.png" style="width: 302px; " /></p>

<pre>
<strong>输入：</strong>l1 = [7,2,4,3], l2 = [5,6,4]
<strong>输出：</strong>[7,8,0,7]
</pre>

<p><strong>示例2：</strong></p>

<pre>
<strong>输入：</strong>l1 = [2,4,3], l2 = [5,6,4]
<strong>输出：</strong>[8,0,7]
</pre>

<p><strong>示例3：</strong></p>

<pre>
<strong>输入：</strong>l1 = [0], l2 = [0]
<strong>输出：</strong>[0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>链表的长度范围为<code> [1, 100]</code></li>
	<li><code>0 &lt;= node.val &lt;= 9</code></li>
	<li>输入数据保证链表代表的数字无前导 0</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>如果输入链表不能翻转该如何解决？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：翻转**

手动翻转链表 `l1` 与 `l2`，将此题转换为 [2. 两数相加](https://leetcode.cn/problems/add-two-numbers/)，相加过程一致。对于最后返回的结果链表也需要进行翻转，共计三次。

**方法二：栈**

进阶条件限制，不可翻转。可以利用两个栈，分别存储两个链表元素。然后对两个栈中对应元素相加，并记录进位 carry。

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
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        s1, s2 = [], []
        while l1:
            s1.append(l1.val)
            l1 = l1.next
        while l2:
            s2.append(l2.val)
            l2 = l2.next
        carry, dummy = 0, ListNode()
        while s1 or s2 or carry:
            carry += (0 if not s1 else s1.pop()) + (0 if not s2 else s2.pop())
            carry, val = divmod(carry, 10)
            node = ListNode(val, dummy.next)
            dummy.next = node
        return dummy.next
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> s1 = new ArrayDeque<>();
        Deque<Integer> s2 = new ArrayDeque<>();
        for (; l1 != null; l1 = l1.next) {
            s1.push(l1.val);
        }
        for (; l2 != null; l2 = l2.next) {
            s2.push(l2.val);
        }
        int carry = 0;
        ListNode dummy = new ListNode();
        while (!s1.isEmpty() || !s2.isEmpty() || carry != 0) {
            carry += (s1.isEmpty() ? 0 : s1.pop()) + (s2.isEmpty() ? 0 : s2.pop());
            ListNode node = new ListNode(carry % 10, dummy.next);
            dummy.next = node;
            carry /= 10;
        }
        return dummy.next;
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
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        stack<int> s1;
        stack<int> s2;
        for (; l1; l1 = l1->next) s1.push(l1->val);
        for (; l2; l2 = l2->next) s2.push(l2->val);
        int carry = 0;
        ListNode* dummy = new ListNode();
        while (!s1.empty() || !s2.empty() || carry) {
            if (!s1.empty()) {
                carry += s1.top();
                s1.pop();
            }
            if (!s2.empty()) {
                carry += s2.top();
                s2.pop();
            }
            ListNode* node = new ListNode(carry % 10, dummy->next);
            dummy->next = node;
            carry /= 10;
        }
        return dummy->next;
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
func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	s1, s2 := arraystack.New(), arraystack.New()
	for l1 != nil {
		s1.Push(l1.Val)
		l1 = l1.Next
	}
	for l2 != nil {
		s2.Push(l2.Val)
		l2 = l2.Next
	}
	carry, dummy := 0, new(ListNode)
	for !s1.Empty() || !s2.Empty() || carry > 0 {
		v, ok := s1.Pop()
		if ok {
			carry += v.(int)
		}
		v, ok = s2.Pop()
		if ok {
			carry += v.(int)
		}
		node := &ListNode{Val: carry % 10, Next: dummy.Next}
		dummy.Next = node
		carry /= 10
	}
	return dummy.Next
}
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

const reverse = (head: ListNode | null) => {
    let pre = null;
    while (head != null) {
        const { next } = head;
        head.next = pre;
        pre = head;
        head = next;
    }
    return pre;
};

function addTwoNumbers(
    l1: ListNode | null,
    l2: ListNode | null,
): ListNode | null {
    l1 = reverse(l1);
    l2 = reverse(l2);
    const dummy = new ListNode();
    let cur = dummy;
    let sum = 0;
    while (l1 != null || l2 != null || sum !== 0) {
        if (l1 != null) {
            sum += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            sum += l2.val;
            l2 = l2.next;
        }
        cur.next = new ListNode(sum % 10);
        cur = cur.next;
        sum = Math.floor(sum / 10);
    }
    return reverse(dummy.next);
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

const createStack = (head: ListNode | null) => {
    const res = [];
    while (head != null) {
        res.push(head.val);
        head = head.next;
    }
    return res;
};

function addTwoNumbers(
    l1: ListNode | null,
    l2: ListNode | null,
): ListNode | null {
    const stack1 = createStack(l1);
    const stack2 = createStack(l2);
    const dummy = new ListNode();
    let sum = 0;
    while (stack1.length !== 0 || stack2.length !== 0 || sum !== 0) {
        sum += (stack1.pop() ?? 0) + (stack2.pop() ?? 0);
        dummy.next = new ListNode(sum % 10, dummy.next);
        sum = Math.floor(sum / 10);
    }
    return dummy.next;
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
impl Solution {
    fn reverse(mut head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut pre = None;
        while let Some(mut node) = head {
            let next = node.next.take();
            node.next = pre.take();
            pre = Some(node);
            head = next;
        }
        pre
    }

    pub fn add_two_numbers(
        mut l1: Option<Box<ListNode>>,
        mut l2: Option<Box<ListNode>>,
    ) -> Option<Box<ListNode>> {
        l1 = Self::reverse(l1);
        l2 = Self::reverse(l2);
        let mut dummy = Some(Box::new(ListNode::new(0)));
        let mut cur = &mut dummy;
        let mut sum = 0;
        while l1.is_some() || l2.is_some() || sum != 0 {
            if let Some(node) = l1 {
                sum += node.val;
                l1 = node.next;
            }
            if let Some(node) = l2 {
                sum += node.val;
                l2 = node.next;
            }
            cur.as_mut().unwrap().next = Some(Box::new(ListNode::new(sum % 10)));
            cur = &mut cur.as_mut().unwrap().next;
            sum /= 10;
        }
        Self::reverse(dummy.unwrap().next.take())
    }
}
```

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
impl Solution {
    fn create_stack(mut head: Option<Box<ListNode>>) -> Vec<i32> {
        let mut res = vec![];
        while let Some(node) = head {
            res.push(node.val);
            head = node.next;
        }
        res
    }

    pub fn add_two_numbers(
        l1: Option<Box<ListNode>>,
        l2: Option<Box<ListNode>>,
    ) -> Option<Box<ListNode>> {
        let mut stack1 = Self::create_stack(l1);
        let mut stack2 = Self::create_stack(l2);

        let mut dummy = Box::new(ListNode::new(0));
        let mut sum = 0;
        while !stack1.is_empty() || !stack2.is_empty() || sum != 0 {
            if let Some(val) = stack1.pop() {
                sum += val;
            }
            if let Some(val) = stack2.pop() {
                sum += val;
            }
            dummy.next = Some(Box::new(ListNode {
                val: sum % 10,
                next: dummy.next.take(),
            }));
            sum /= 10;
        }
        dummy.next.take()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
