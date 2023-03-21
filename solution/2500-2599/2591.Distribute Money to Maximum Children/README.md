# [2591. 将钱分给最多的儿童](https://leetcode.cn/problems/distribute-money-to-maximum-children)

[English Version](/solution/2500-2599/2591.Distribute%20Money%20to%20Maximum%20Children/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>money</code>&nbsp;，表示你总共有的钱数（单位为美元）和另一个整数&nbsp;<code>children</code>&nbsp;，表示你要将钱分配给多少个儿童。</p>

<p>你需要按照如下规则分配：</p>

<ul>
	<li>所有的钱都必须被分配。</li>
	<li>每个儿童至少获得&nbsp;<code>1</code>&nbsp;美元。</li>
	<li>没有人获得 <code>4</code>&nbsp;美元。</li>
</ul>

<p>请你按照上述规则分配金钱，并返回 <strong>最多</strong>&nbsp;有多少个儿童获得 <strong>恰好</strong><em>&nbsp;</em><code>8</code>&nbsp;美元。如果没有任何分配方案，返回&nbsp;<code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>money = 20, children = 3
<b>输出：</b>1
<b>解释：</b>
最多获得 8 美元的儿童数为 1 。一种分配方案为：
- 给第一个儿童分配 8 美元。
- 给第二个儿童分配 9 美元。
- 给第三个儿童分配 3 美元。
没有分配方案能让获得 8 美元的儿童数超过 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>money = 16, children = 2
<b>输出：</b>2
<b>解释：</b>每个儿童都可以获得 8 美元。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= money &lt;= 200</code></li>
	<li><code>2 &lt;= children &lt;= 30</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：分类讨论**

如果 $money \lt children$，那么一定存在儿童没有分到钱，返回 $-1$。

如果 $money \gt 8 \times children$，那么有 $children-1$ 个儿童获得了 $8$ 美元，剩下的一个儿童获得了 $money - 8 \times (children-1)$ 美元，返回 $children-1$。

如果 $money = 8 \times children - 4$，那么有 $children-2$ 个儿童获得了 $8$ 美元，剩下的两个儿童分摊剩下的 $12$ 美元（只要不是 $4$, $8$ 美元就行），返回 $children-2$。

如果，我们假设有 $x$ 个儿童获得了 $8$ 美元，那么剩下的钱为 $money- 8 \times x$，只要保证大于等于剩下的儿童数 $children-x$，就可以满足题意。因此，我们只需要求出 $x$ 的最大值，即为答案。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def distMoney(self, money: int, children: int) -> int:
        if money < children:
            return -1
        if money > 8 * children:
            return children - 1
        if money == 8 * children - 4:
            return children - 2
        # money-8x >= children-x, x <= (money-children)/7
        return (money - children) // 7
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int distMoney(int money, int children) {
        if (money < children) {
            return -1;
        }
        if (money > 8 * children) {
            return children - 1;
        }
        if (money == 8 * children - 4) {
            return children - 2;
        }
        // money-8x >= children-x, x <= (money-children)/7
        return (money - children) / 7;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int distMoney(int money, int children) {
        if (money < children) {
            return -1;
        }
        if (money > 8 * children) {
            return children - 1;
        }
        if (money == 8 * children - 4) {
            return children - 2;
        }
        // money-8x >= children-x, x <= (money-children)/7
        return (money - children) / 7;
    }
};
```

### **Go**

```go
func distMoney(money int, children int) int {
	if money < children {
		return -1
	}
	if money > 8*children {
		return children - 1
	}
	if money == 8*children-4 {
		return children - 2
	}
	// money-8x >= children-x, x <= (money-children)/7
	return (money - children) / 7
}
```

### **TypeScript**

```ts
function distMoney(money: number, children: number): number {
    if (money < children) {
        return -1;
    }
    if (money > 8 * children) {
        return children - 1;
    }
    if (money === 8 * children - 4) {
        return children - 2;
    }
    return Math.floor((money - children) / 7);
}
```

### **...**

```

```

<!-- tabs:end -->
