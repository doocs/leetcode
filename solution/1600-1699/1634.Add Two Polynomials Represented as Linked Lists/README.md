# [1634. 求两个多项式链表的和](https://leetcode.cn/problems/add-two-polynomials-represented-as-linked-lists)

[English Version](/solution/1600-1699/1634.Add%20Two%20Polynomials%20Represented%20as%20Linked%20Lists/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>多项式链表是一种特殊形式的链表，每个节点表示多项式的一项。</p>

<p>每个节点有三个属性：</p>

<ul>
	<li><code>coefficient</code>：该项的系数。项 <code><strong>9</strong>x<sup>4</sup></code> 的系数是 <code>9</code> 。</li>
	<li><code>power</code>：该项的指数。项 <code>9x<strong><sup>4</sup></strong></code> 的指数是 <code>4</code> 。</li>
	<li><code>next</code>：指向下一个节点的指针（引用），如果当前节点为链表的最后一个节点则为 <code>null</code> 。</li>
</ul>

<p>例如，多项式 <code>5x<sup>3</sup> + 4x - 7</code> 可以表示成如下图所示的多项式链表：</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1634.Add%20Two%20Polynomials%20Represented%20as%20Linked%20Lists/images/polynomial2.png" style="width: 500px; height: 91px;" /></p>

<p>多项式链表必须是标准形式的，即多项式必须<strong> 严格 </strong>按指数 <code>power</code> 的递减顺序排列（即降幂排列）。另外，系数 <code>coefficient</code> 为 <code>0</code> 的项需要省略。</p>

<p>给定两个多项式链表的头节点 <code>poly1</code> 和 <code>poly2</code>，返回它们的和的头节点。</p>

<p><strong><code>PolyNode</code> 格式：</strong></p>

<p>输入/输出格式表示为 <code>n</code> 个节点的列表，其中每个节点表示为 <code>[coefficient, power]</code> 。例如，多项式 <code>5x<sup>3</sup> + 4x - 7</code> 表示为： <code>[[5,3],[4,1],[-7,0]]</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1634.Add%20Two%20Polynomials%20Represented%20as%20Linked%20Lists/images/ex1.png" style="width: 600px; height: 322px;" /></p>

<pre>
<strong>输入：</strong>poly1 = [[1,1]], poly2 = [[1,0]]
<strong>输出：</strong>[[1,1],[1,0]]
<strong>解释：</strong>poly1 = x. poly2 = 1. 和为 x + 1.
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>poly1 = [[2,2],[4,1],[3,0]], poly2 = [[3,2],[-4,1],[-1,0]]
<strong>输出：</strong>[[5,2],[2,0]]
<strong>解释：</strong>poly1 = 2x<sup>2</sup> + 4x + 3. poly2 = 3x<sup>2</sup> - 4x - 1. 和为 5x<sup>2</sup> + 2. 注意，我们省略 "0x" 项。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>poly1 = [[1,2]], poly2 = [[-1,2]]
<strong>输出：</strong>[]
<strong>解释：</strong>和为 0。我们返回空链表。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= n <= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> <= PolyNode.coefficient <= 10<sup>9</sup></code></li>
	<li><code>PolyNode.coefficient != 0</code></li>
	<li><code>0 <= PolyNode.power <= 10<sup>9</sup></code></li>
	<li><code>PolyNode.power > PolyNode.next.power</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：遍历链表**

我们可以同时遍历两个链表，根据指数大小关系，将节点添加到结果链表中。

最后，如果链表 $1$ 或链表 $2$ 还有剩余节点，将其添加到结果链表中。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为两个链表中节点数的较大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for polynomial singly-linked list.
# class PolyNode:
#     def __init__(self, x=0, y=0, next=None):
#         self.coefficient = x
#         self.power = y
#         self.next = next


class Solution:
    def addPoly(self, poly1: "PolyNode", poly2: "PolyNode") -> "PolyNode":
        dummy = curr = PolyNode()
        while poly1 and poly2:
            if poly1.power > poly2.power:
                curr.next = poly1
                poly1 = poly1.next
                curr = curr.next
            elif poly1.power < poly2.power:
                curr.next = poly2
                poly2 = poly2.next
                curr = curr.next
            else:
                if c := poly1.coefficient + poly2.coefficient:
                    curr.next = PolyNode(c, poly1.power)
                    curr = curr.next
                poly1 = poly1.next
                poly2 = poly2.next
        curr.next = poly1 or poly2
        return dummy.next
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
/**
 * Definition for polynomial singly-linked list.
 * class PolyNode {
 *     int coefficient, power;
 *     PolyNode next = null;

 *     PolyNode() {}
 *     PolyNode(int x, int y) { this.coefficient = x; this.power = y; }
 *     PolyNode(int x, int y, PolyNode next) { this.coefficient = x; this.power = y; this.next =
 next; }
 * }
 */

class Solution {
    public PolyNode addPoly(PolyNode poly1, PolyNode poly2) {
        PolyNode dummy = new PolyNode();
        PolyNode curr = dummy;
        while (poly1 != null && poly2 != null) {
            if (poly1.power > poly2.power) {
                curr.next = poly1;
                poly1 = poly1.next;
                curr = curr.next;
            } else if (poly1.power < poly2.power) {
                curr.next = poly2;
                poly2 = poly2.next;
                curr = curr.next;
            } else {
                int c = poly1.coefficient + poly2.coefficient;
                if (c != 0) {
                    curr.next = new PolyNode(c, poly1.power);
                    curr = curr.next;
                }
                poly1 = poly1.next;
                poly2 = poly2.next;
            }
        }
        if (poly1 == null) {
            curr.next = poly2;
        }
        if (poly2 == null) {
            curr.next = poly1;
        }
        return dummy.next;
    }
}
```

### **C++**

```cpp
/**
 * Definition for polynomial singly-linked list->
 * struct PolyNode {
 *     int coefficient, power;
 *     PolyNode *next;
 *     PolyNode(): coefficient(0), power(0), next(nullptr) {};
 *     PolyNode(int x, int y): coefficient(x), power(y), next(nullptr) {};
 *     PolyNode(int x, int y, PolyNode* next): coefficient(x), power(y), next(next) {};
 * };
 */

class Solution {
public:
    PolyNode* addPoly(PolyNode* poly1, PolyNode* poly2) {
        PolyNode* dummy = new PolyNode();
        PolyNode* curr = dummy;
        while (poly1 && poly2) {
            if (poly1->power > poly2->power) {
                curr->next = poly1;
                poly1 = poly1->next;
                curr = curr->next;
            } else if (poly1->power < poly2->power) {
                curr->next = poly2;
                poly2 = poly2->next;
                curr = curr->next;
            } else {
                int c = poly1->coefficient + poly2->coefficient;
                if (c != 0) {
                    curr->next = new PolyNode(c, poly1->power);
                    curr = curr->next;
                }
                poly1 = poly1->next;
                poly2 = poly2->next;
            }
        }
        if (!poly1) {
            curr->next = poly2;
        }
        if (!poly2) {
            curr->next = poly1;
        }
        return dummy->next;
    }
};
```

### **C#**

```cs
/**
 * Definition for polynomial singly-linked list.
 * public class PolyNode {
 *     public int coefficient, power;
 *     public PolyNode next;
 *
 *     public PolyNode(int x=0, int y=0, PolyNode next=null) {
 *         this.coefficient = x;
 *         this.power = y;
 *         this.next = next;
 *     }
 * }
 */

public class Solution {
    public PolyNode AddPoly(PolyNode poly1, PolyNode poly2) {
        PolyNode dummy = new PolyNode();
        PolyNode curr = dummy;
        while (poly1 != null && poly2 != null) {
            if (poly1.power > poly2.power) {
                curr.next = poly1;
                poly1 = poly1.next;
                curr = curr.next;
            } else if (poly1.power < poly2.power) {
                curr.next = poly2;
                poly2 = poly2.next;
                curr = curr.next;
            } else {
                int c = poly1.coefficient + poly2.coefficient;
                if (c != 0) {
                    curr.next = new PolyNode(c, poly1.power);
                    curr = curr.next;
                }
                poly1 = poly1.next;
                poly2 = poly2.next;
            }
        }
        if (poly1 == null) {
            curr.next = poly2;
        }
        if (poly2 == null) {
            curr.next = poly1;
        }
        return dummy.next;
    }
}
```

### **JavaScript**

```js
/**
 * Definition for polynomial singly-linked list.
 * function PolyNode(x=0, y=0, next=null) {
 *     this.coefficient = x;
 *     this.power = y;
 *     this.next = next;
 * }
 */

/**
 * @param {PolyNode} poly1
 * @param {PolyNode} poly2
 * @return {PolyNode}
 */
var addPoly = function (poly1, poly2) {
    const dummy = new PolyNode();
    let curr = dummy;
    while (poly1 && poly2) {
        if (poly1.power > poly2.power) {
            curr.next = poly1;
            poly1 = poly1.next;
            curr = curr.next;
        } else if (poly1.power < poly2.power) {
            curr.next = poly2;
            poly2 = poly2.next;
            curr = curr.next;
        } else {
            const c = poly1.coefficient + poly2.coefficient;
            if (c != 0) {
                curr.next = new PolyNode(c, poly1.power);
                curr = curr.next;
            }
            poly1 = poly1.next;
            poly2 = poly2.next;
        }
    }
    curr.next = poly1 || poly2;
    return dummy.next;
};
```

### **...**

```

```

<!-- tabs:end -->
