# [382. 链表随机节点](https://leetcode.cn/problems/linked-list-random-node)

[English Version](/solution/0300-0399/0382.Linked%20List%20Random%20Node/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点<strong> 被选中的概率一样</strong> 。</p>

<p>实现 <code>Solution</code> 类：</p>

<ul>
	<li><code>Solution(ListNode head)</code> 使用整数数组初始化对象。</li>
	<li><code>int getRandom()</code> 从链表中随机选择一个节点并返回该节点的值。链表中所有节点被选中的概率相等。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0382.Linked%20List%20Random%20Node/images/getrand-linked-list.jpg" style="width: 302px; height: 62px;" />
<pre>
<strong>输入</strong>
["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
[[[1, 2, 3]], [], [], [], [], []]
<strong>输出</strong>
[null, 1, 3, 2, 2, 3]

<strong>解释</strong>
Solution solution = new Solution([1, 2, 3]);
solution.getRandom(); // 返回 1
solution.getRandom(); // 返回 3
solution.getRandom(); // 返回 2
solution.getRandom(); // 返回 2
solution.getRandom(); // 返回 3
// getRandom() 方法应随机返回 1、2、3 中的一个，每个元素被返回的概率相等。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>链表中的节点数在范围 <code>[1, 10<sup>4</sup>]</code> 内</li>
	<li><code>-10<sup>4</sup> &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
	<li>至多调用&nbsp;<code>getRandom</code> 方法 <code>10<sup>4</sup></code> 次</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<ul>
	<li>如果链表非常大且长度未知，该怎么处理？</li>
	<li>你能否在不使用额外空间的情况下解决此问题？</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

蓄水池抽样问题。即从一个包含 n 个对象的列表 S 中随机选取 k 个对象，n 为一个非常大或者不知道的值。通常情况下，n 是一个非常大的值，大到无法一次性把所有列表 S 中的对象都放到内存中。我们这个问题是蓄水池抽样问题的一个特例，即 k=1。

**解法**：我们总是选择第一个对象，以 1/2 的概率选择第二个，以 1/3 的概率选择第三个，以此类推，以 1/m 的概率选择第 m 个对象。当该过程结束时，每一个对象具有相同的选中概率，即 1/n。

**证明**：第 m 个对象最终被选中的概率 P = `选择 m 的概率 × 其后面所有对象不被选择的概率`，即：

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0382.Linked%20List%20Random%20Node/images/demo.gif"/>

思路同：[398. 随机数索引](/solution/0300-0399/0398.Random%20Pick%20Index/README.md)

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
    def __init__(self, head: Optional[ListNode]):
        self.head = head

    def getRandom(self) -> int:
        n = ans = 0
        head = self.head
        while head:
            n += 1
            x = random.randint(1, n)
            if n == x:
                ans = head.val
            head = head.next
        return ans


# Your Solution object will be instantiated and called as such:
# obj = Solution(head)
# param_1 = obj.getRandom()
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
    private ListNode head;
    private Random random = new Random();

    public Solution(ListNode head) {
        this.head = head;
    }

    public int getRandom() {
        int ans = 0, n = 0;
        for (ListNode node = head; node != null; node = node.next) {
            ++n;
            int x = 1 + random.nextInt(n);
            if (n == x) {
                ans = node.val;
            }
        }
        return ans;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
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
    ListNode* head;

    Solution(ListNode* head) {
        this->head = head;
    }

    int getRandom() {
        int n = 0, ans = 0;
        for (ListNode* node = head; node != nullptr; node = node->next) {
            n += 1;
            int x = 1 + rand() % n;
            if (n == x) ans = node->val;
        }
        return ans;
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(head);
 * int param_1 = obj->getRandom();
 */
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
type Solution struct {
	head *ListNode
}

func Constructor(head *ListNode) Solution {
	return Solution{head}
}

func (this *Solution) GetRandom() int {
	n, ans := 0, 0
	for node := this.head; node != nil; node = node.Next {
		n++
		x := 1 + rand.Intn(n)
		if n == x {
			ans = node.Val
		}
	}
	return ans
}

/**
 * Your Solution object will be instantiated and called as such:
 * obj := Constructor(head);
 * param_1 := obj.GetRandom();
 */
```

### **...**

```

```

<!-- tabs:end -->
