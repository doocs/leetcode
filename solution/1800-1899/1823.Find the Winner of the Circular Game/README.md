# [1823. 找出游戏的获胜者](https://leetcode.cn/problems/find-the-winner-of-the-circular-game)

[English Version](/solution/1800-1899/1823.Find%20the%20Winner%20of%20the%20Circular%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>共有 <code>n</code> 名小伙伴一起做游戏。小伙伴们围成一圈，按 <strong>顺时针顺序</strong> 从 <code>1</code> 到 <code>n</code> 编号。确切地说，从第 <code>i</code> 名小伙伴顺时针移动一位会到达第 <code>(i+1)</code> 名小伙伴的位置，其中 <code>1 &lt;= i &lt; n</code> ，从第 <code>n</code> 名小伙伴顺时针移动一位会回到第 <code>1</code> 名小伙伴的位置。</p>

<p>游戏遵循如下规则：</p>

<ol>
	<li>从第 <code>1</code> 名小伙伴所在位置 <strong>开始</strong> 。</li>
	<li>沿着顺时针方向数 <code>k</code> 名小伙伴，计数时需要 <strong>包含</strong> 起始时的那位小伙伴。逐个绕圈进行计数，一些小伙伴可能会被数过不止一次。</li>
	<li>你数到的最后一名小伙伴需要离开圈子，并视作输掉游戏。</li>
	<li>如果圈子中仍然有不止一名小伙伴，从刚刚输掉的小伙伴的 <strong>顺时针下一位</strong> 小伙伴 <strong>开始</strong>，回到步骤 <code>2</code> 继续执行。</li>
	<li>否则，圈子中最后一名小伙伴赢得游戏。</li>
</ol>

<p>给你参与游戏的小伙伴总数 <code>n</code> ，和一个整数 <code>k</code> ，返回游戏的获胜者。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1823.Find%20the%20Winner%20of%20the%20Circular%20Game/images/ic234-q2-ex11.png" style="width: 500px; height: 345px;" />
<pre>
<strong>输入：</strong>n = 5, k = 2
<strong>输出：</strong>3
<strong>解释：</strong>游戏运行步骤如下：
1) 从小伙伴 1 开始。
2) 顺时针数 2 名小伙伴，也就是小伙伴 1 和 2 。
3) 小伙伴 2 离开圈子。下一次从小伙伴 3 开始。
4) 顺时针数 2 名小伙伴，也就是小伙伴 3 和 4 。
5) 小伙伴 4 离开圈子。下一次从小伙伴 5 开始。
6) 顺时针数 2 名小伙伴，也就是小伙伴 5 和 1 。
7) 小伙伴 1 离开圈子。下一次从小伙伴 3 开始。
8) 顺时针数 2 名小伙伴，也就是小伙伴 3 和 5 。
9) 小伙伴 5 离开圈子。只剩下小伙伴 3 。所以小伙伴 3 是游戏的获胜者。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 6, k = 5
<strong>输出：</strong>1
<strong>解释：</strong>小伙伴离开圈子的顺序：5、4、6、2、3 。小伙伴 1 是游戏的获胜者。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= n &lt;= 500</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能否使用线性时间复杂度和常数空间复杂度解决此问题？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

约瑟夫环。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findTheWinner(self, n: int, k: int) -> int:
        if n == 1:
            return 1
        ans = (k + self.findTheWinner(n - 1, k)) % n
        return n if ans == 0 else ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findTheWinner(int n, int k) {
        if (n == 1) {
            return 1;
        }
        int ans = (findTheWinner(n - 1, k) + k) % n;
        return ans == 0 ? n : ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findTheWinner(int n, int k) {
        if (n == 1) return 1;
        int ans = (findTheWinner(n - 1, k) + k) % n;
        return ans == 0 ? n : ans;
    }
};
```

### **Go**

```go
func findTheWinner(n int, k int) int {
	if n == 1 {
		return 1
	}
	ans := (findTheWinner(n-1, k) + k) % n
	if ans == 0 {
		return n
	}
	return ans
}
```

### **TypeScript**

```ts
class LinkNode {
    public val: number;
    public next: LinkNode;

    constructor(val: number = 0, next?: LinkNode) {
        this.val = val;
        this.next = next;
    }
}

function findTheWinner(n: number, k: number): number {
    if (k === 1) {
        return n;
    }
    const dummy = new LinkNode(0);
    let cur = dummy;
    for (let i = 1; i <= n; i++) {
        cur.next = new LinkNode(i);
        cur = cur.next;
    }
    cur.next = dummy.next;

    cur = dummy;
    let count = 0;
    while (cur.next != cur) {
        count++;
        if (count === k) {
            cur.next = cur.next.next;
            count = 0;
        } else {
            cur = cur.next;
        }
    }
    return cur.val;
}
```

### **...**

```

```

<!-- tabs:end -->
