# [LCP 06. 拿硬币](https://leetcode.cn/problems/na-ying-bi)

## 题目描述

<!-- 这里写题目描述 -->

<p>桌上有 <code>n</code> 堆力扣币，每堆的数量保存在数组 <code>coins</code> 中。我们每次可以选择任意一堆，拿走其中的一枚或者两枚，求拿完所有力扣币的最少次数。</p>

<p><strong>示例 1：</strong></p>

<blockquote>
<p>输入：<code>[4,2,1]</code></p>

<p>输出：<code>4</code></p>

<p>解释：第一堆力扣币最少需要拿 2 次，第二堆最少需要拿 1 次，第三堆最少需要拿 1 次，总共 4 次即可拿完。</p>
</blockquote>

<p><strong>示例 2：</strong></p>

<blockquote>
<p>输入：<code>[2,3,10]</code></p>

<p>输出：<code>8</code></p>
</blockquote>

<p><strong>限制：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 4</code></li>
	<li><code>1 &lt;= coins[i] &lt;= 10</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minCount(self, coins: List[int]) -> int:
        return sum((coin + 1) // 2 for coin in coins)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minCount(int[] coins) {
        int ans = 0;
        for (int coin : coins) {
            ans += (coin + 1) / 2;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minCount(vector<int>& coins) {
        int ans = 0;
        for (int coin : coins) ans += (coin + 1) / 2;
        return ans;
    }
};
```

### **Go**

```go
func minCount(coins []int) int {
	ans := 0
	for _, coin := range coins {
		ans += (coin + 1) / 2
	}
	return ans
}
```

### **C**

```c
int minCount(int* coins, int coinsSize) {
    int res = 0;
    for (int i = 0; i < coinsSize; i++) {
        int coin = coins[i];
        if (coin % 2 == 1) {
            res++;
        }
        res += coin / 2;
    }
    return res;
}
```

### **...**

```

```

<!-- tabs:end -->
