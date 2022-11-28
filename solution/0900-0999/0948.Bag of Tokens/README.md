# [948. 令牌放置](https://leetcode.cn/problems/bag-of-tokens)

[English Version](/solution/0900-0999/0948.Bag%20of%20Tokens/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你的初始 <strong>能量</strong> 为 <code>power</code>，初始 <strong>分数</strong> 为&nbsp;<code>0</code>，只有一包令牌 <code>tokens</code> 。其中 <code>tokens[i]</code> 是第 <code>i</code> 个令牌的值（下标从 0 开始）。</p>

<p>令牌可能的两种使用方法如下：</p>

<ul>
	<li>如果你至少有&nbsp;<code>token[i]</code>&nbsp;点 <strong>能量</strong> ，可以将令牌 <code>i</code> 置为正面朝上，失去&nbsp;<code>token[i]</code>&nbsp;点 <strong>能量</strong> ，并得到&nbsp;<code>1</code>&nbsp;<strong>分</strong> 。</li>
	<li>如果我们至少有&nbsp;<code>1</code>&nbsp;<strong>分 </strong>，可以将令牌 <code>i</code> 置为反面朝上，获得&nbsp;<code>token[i]</code> 点 <strong>能量</strong> ，并失去&nbsp;<code>1</code>&nbsp;<strong>分</strong> 。</li>
</ul>

<p>每个令牌 <strong>最多</strong> 只能使用一次，使用 <strong>顺序不限</strong> ，<strong>不需</strong> 使用所有令牌。</p>

<p>在使用任意数量的令牌后，返回我们可以得到的最大 <strong>分数</strong> 。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>tokens = [100], power = 50
<strong>输出：</strong>0
<strong>解释：</strong>无法使用唯一的令牌，因为能量和分数都太少了。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>tokens = [100,200], power = 150
<strong>输出：</strong>1
<strong>解释：</strong>令牌 0 正面朝上，能量变为 50，分数变为 1 。
不必使用令牌 1 ，因为你无法使用它来提高分数。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>tokens = [100,200,300,400], power = 200
<strong>输出：</strong>2
<strong>解释：</strong>按下面顺序使用令牌可以得到 2 分：
1. 令牌 0 正面朝上，能量变为 100 ，分数变为 1
2. 令牌 3 正面朝下，能量变为 500 ，分数变为 0
3. 令牌 1 正面朝上，能量变为 300 ，分数变为 1
4. 令牌 2 正面朝上，能量变为 0 ，分数变为 2</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= tokens.length &lt;= 1000</code></li>
	<li><code>0 &lt;= tokens[i],&nbsp;power &lt; 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 排序 + 双指针**

令牌的使用方法有两种，一种是消耗能量得到分数，一种是消耗分数得到能量。显然，我们应该消耗尽可能少的能量来得到尽可能多的分数。

因此，我们可以将令牌按照消耗能量的多少进行排序，然后使用双指针，一个指针从左向右遍历，一个指针从右向左遍历，每次遍历都尽可能地消耗能量得到分数，然后更新最大分数。如果当前能量不足以消耗当前令牌，那么我们就尝试使用分数来消耗当前令牌，如果分数不足以消耗当前令牌，那么我们就停止遍历。

时间复杂度 $O(n\log n)$，空间复杂度 $O(n)$。其中 $n$ 为令牌的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def bagOfTokensScore(self, tokens: List[int], power: int) -> int:
        tokens.sort()
        i, j = 0, len(tokens) - 1
        ans = t = 0
        while i <= j:
            if power >= tokens[i]:
                power -= tokens[i]
                i, t = i + 1, t + 1
                ans = max(ans, t)
            elif t:
                power += tokens[j]
                j, t = j - 1, t - 1
            else:
                break
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int i = 0, j = tokens.length - 1;
        int ans = 0, t = 0;
        while (i <= j) {
            if (power >= tokens[i]) {
                power -= tokens[i++];
                ++t;
                ans = Math.max(ans, t);
            } else if (t > 0) {
                power += tokens[j--];
                --t;
            } else {
                break;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int bagOfTokensScore(vector<int>& tokens, int power) {
        sort(tokens.begin(), tokens.end());
        int i = 0, j = tokens.size() - 1;
        int ans = 0, t = 0;
        while (i <= j) {
            if (power >= tokens[i]) {
                power -= tokens[i++];
                ans = max(ans, ++t);
            } else if (t) {
                power += tokens[j--];
                --t;
            } else {
                break;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func bagOfTokensScore(tokens []int, power int) int {
	sort.Ints(tokens)
	i, j := 0, len(tokens)-1
	ans, t := 0, 0
	for i <= j {
		if power >= tokens[i] {
			power -= tokens[i]
			i, t = i+1, t+1
			ans = max(ans, t)
		} else if t > 0 {
			power += tokens[j]
			j, t = j-1, t-1
		} else {
			break
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
