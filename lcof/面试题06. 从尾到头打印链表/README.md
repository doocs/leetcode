# [面试题 06. 从尾到头打印链表](https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/)

## 题目描述

输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

**示例 1：**

```
输入：head = [1,3,2]
输出：[2,3,1]
```

**限制：**

- `0 <= 链表长度 <= 10000`

## 解法

栈实现。或者其它方式，见题解。

<!-- tabs:start -->

### **Python3**

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def reversePrint(self, head: ListNode) -> List[int]:
        res = []
        while head:
            res.append(head.val)
            head = head.next
        return res[::-1]
```

### **Java**

- 栈实现：

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int[] reversePrint(ListNode head) {
        Stack<Integer> s = new Stack<>();
        while (head != null) {
            s.push(head.val);
            head = head.next;
        }
        int[] res = new int[s.size()];
        int i = 0;
        while (!s.isEmpty()) {
            res[i++] = s.pop();
        }
        return res;
    }
}
```

- 先计算链表长度 n，然后创建一个长度为 n 的结果数组。最后遍历链表，依次将节点值存放在数组上（从后往前）。

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int[] reversePrint(ListNode head) {
        if (head == null) return new int[]{};
        // 计算链表长度n
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            ++n;
            cur = cur.next;
        }
        int[] res = new int[n];
        cur = head;
        while (cur != null) {
            res[--n] = cur.val;
            cur = cur.next;
        }
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} head
 * @return {number[]}
 */
var reversePrint = function (head) {
    let res = [];
    while (head != null) {
        res.unshift(head.val);
        head = head.next;
    }
    return res;
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
//insert to the front
func reversePrint(head *ListNode) []int {
	res := []int{}
	for head != nil {
		res = append([]int{head.Val}, res...)
		head = head.Next
	}
	return res
}
```

### **C++**

- 递归实现

```cpp
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    vector<int> ret;

    void getVal(ListNode* head) {
        // 这里可以看成是一个节点的树
        if (head) {
            if (head->next) {
                getVal(head->next);
            }
            ret.push_back(head->val);
        }
    }

    vector<int> reversePrint(ListNode* head) {
        getVal(head);
        // 返回的是全局的ret信息。在getVal函数中被赋值
        return ret;
    }
};
```

- 栈实现

```cpp
class Solution {
public:
    vector<int> reversePrint(ListNode* head) {
        stack<int> stk;
        vector<int> ans;
        ListNode *p = head;
        while (p) {
            stk.push(p->val);
            p = p->next;
        }
        while (!stk.empty()) {
            ans.push_back(stk.top());
            stk.pop();
        }
        return ans;
    }
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

function reversePrint(head: ListNode | null): number[] {
    let res: number[] = [];
    while (head != null) {
        res.unshift(head.val);
        head = head.next;
    }
    return res;
}
```

### **...**

```

```

<!-- tabs:end -->
