# [914. 卡牌分组](https://leetcode.cn/problems/x-of-a-kind-in-a-deck-of-cards)

[English Version](/solution/0900-0999/0914.X%20of%20a%20Kind%20in%20a%20Deck%20of%20Cards/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一副牌，每张牌上都写着一个整数。</p>

<p>此时，你需要选定一个数字 <code>X</code>，使我们可以将整副牌按下述规则分成 1 组或更多组：</p>

<ul>
	<li>每组都有&nbsp;<code>X</code>&nbsp;张牌。</li>
	<li>组内所有的牌上都写着相同的整数。</li>
</ul>

<p>仅当你可选的 <code>X &gt;= 2</code> 时返回&nbsp;<code>true</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>deck = [1,2,3,4,4,3,2,1]
<strong>输出：</strong>true
<strong>解释：</strong>可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>deck = [1,1,1,2,2,2,3,3]
<strong>输出：</strong>false
<strong>解释：</strong>没有满足要求的分组。
</pre>

<p><br />
<strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= deck.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= deck[i] &lt; 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：最大公约数**

我们先用数组或哈希表 `cnt` 统计每个数字出现的次数，只有当 $X$ 是所有数字出现次数的约数时，即 $X$ 是所有 `cnt[i]` 的最大公约数的约数时，才能满足题意。

因此，我们求出所有数字出现次数的最大公约数 $g$，然后判断其是否大于等于 $2$ 即可。

时间复杂度 $O(n\log C)$，其中 $n$ 是数组 `deck` 的长度，而 $C$ 是数组 `deck` 中的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def hasGroupsSizeX(self, deck: List[int]) -> bool:
        vals = Counter(deck).values()
        return reduce(gcd, vals) >= 2
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        int[] cnt = new int[10000];
        for (int v : deck) {
            ++cnt[v];
        }
        int g = -1;
        for (int v : cnt) {
            if (v > 0) {
                g = g == -1 ? v : gcd(g, v);
            }
        }
        return g >= 2;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool hasGroupsSizeX(vector<int>& deck) {
        int cnt[10000] = {0};
        for (int& v : deck) ++cnt[v];
        int g = -1;
        for (int& v : cnt) {
            if (v) {
                g = g == -1 ? v : __gcd(g, v);
            }
        }
        return g >= 2;
    }
};
```

### **Go**

```go
func hasGroupsSizeX(deck []int) bool {
	cnt := make([]int, 10000)
	for _, v := range deck {
		cnt[v]++
	}
	g := -1
	for _, v := range cnt {
		if v > 0 {
			if g == -1 {
				g = v
			} else {
				g = gcd(g, v)
			}
		}
	}
	return g >= 2
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

### **...**

```

```

<!-- tabs:end -->
