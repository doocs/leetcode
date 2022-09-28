# [1176. 健身计划评估](https://leetcode.cn/problems/diet-plan-performance)

[English Version](/solution/1100-1199/1176.Diet%20Plan%20Performance/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你的好友是一位健身爱好者。前段日子，他给自己制定了一份健身计划。现在想请你帮他评估一下这份计划是否合理。</p>

<p>他会有一份计划消耗的卡路里表，其中&nbsp;<code>calories[i]</code>&nbsp;给出了你的这位好友在第&nbsp;<code>i</code>&nbsp;天需要消耗的卡路里总量。</p>

<p>为了更好地评估这份计划，对于卡路里表中的每一天，你都需要计算他 「这一天以及之后的连续几天」 （共&nbsp;<code>k</code> 天）内消耗的总卡路里 <em>T：</em></p>

<ul>
	<li>如果&nbsp;<code>T &lt; lower</code>，那么这份计划相对糟糕，并失去 1 分；&nbsp;</li>
	<li>如果 <code>T &gt; upper</code>，那么这份计划相对优秀，并获得 1 分；</li>
	<li>否则，这份计划普普通通，分值不做变动。</li>
</ul>

<p>请返回统计完所有&nbsp;<code>calories.length</code>&nbsp;天后得到的总分作为评估结果。</p>

<p>注意：总分可能是负数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>calories = [1,2,3,4,5], k = 1, lower = 3, upper = 3
<strong>输出：</strong>0
<strong>解释：</strong>calories[0], calories[1] &lt; lower 而 calories[3], calories[4] &gt; upper, 总分 = 0.</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>calories = [3,2], k = 2, lower = 0, upper = 1
<strong>输出：</strong>1
<strong>解释：</strong>calories[0] + calories[1] &gt; upper, 总分 = 1.
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>calories = [6,5,0,0], k = 2, lower = 1, upper = 5
<strong>输出：</strong>0
<strong>解释：</strong>calories[0] + calories[1] &gt; upper, calories[2] + calories[3] &lt; lower, 总分 = 0.
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= calories.length &lt;= 10^5</code></li>
	<li><code>0 &lt;= calories[i] &lt;= 20000</code></li>
	<li><code>0 &lt;= lower &lt;= upper</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和**

先预处理出长度为 $n+1$ 的前缀和数组 $s$，其中 $s[i]$ 表示前 $i$ 天的卡路里总和。

然后遍历前缀和数组 $s$，对于每个位置 $i$，计算 $s[i+k]-s[i]$，即为第 $i$ 天开始的连续 $k$ 天的卡路里总和。根据题意，对于每个 $s[i+k]-s[i]$，判断值与 $lower$ 和 $upper$ 的关系，更新答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `calories` 的长度。

**方法二：滑动窗口**

滑动窗口。维护一个长度为 $k$ 的滑动窗口，窗口内元素之和记为 $s$。如果 $s \lt lower$，则分数减 $1$；如果 $ s \gt upper$，则分数加 $1$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `calories` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def dietPlanPerformance(self, calories: List[int], k: int, lower: int, upper: int) -> int:
        s = list(accumulate(calories, initial=0))
        ans, n = 0, len(calories)
        for i in range(n - k + 1):
            t = s[i + k] - s[i]
            if t < lower:
                ans -= 1
            elif t > upper:
                ans += 1
        return ans
```

```python
class Solution:
    def dietPlanPerformance(self, calories: List[int], k: int, lower: int, upper: int) -> int:
        def check(s):
            if s < lower:
                return -1
            if s > upper:
                return 1
            return 0

        s, n = sum(calories[:k]), len(calories)
        ans = check(s)
        for i in range(k, n):
            s += calories[i] - calories[i - k]
            ans += check(s)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int n = calories.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + calories[i];
        }
        int ans = 0;
        for (int i = 0; i < n - k + 1; ++i) {
            int t = s[i + k] - s[i];
            if (t < lower) {
                --ans;
            } else if (t > upper) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int s = 0, n = calories.length;
        for (int i = 0; i < k; ++i) {
            s += calories[i];
        }
        int ans = 0;
        if (s < lower) {
            --ans;
        } else if (s > upper) {
            ++ans;
        }
        for (int i = k; i < n; ++i) {
            s += calories[i] - calories[i - k];
            if (s < lower) {
                --ans;
            } else if (s > upper) {
                ++ans;
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
    int dietPlanPerformance(vector<int>& calories, int k, int lower, int upper) {
        int n = calories.size();
        vector<int> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + calories[i];
        int ans = 0;
        for (int i = 0; i < n - k + 1; ++i) {
            int t = s[i + k] - s[i];
            if (t < lower) --ans;
            else if (t > upper) ++ans;
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int dietPlanPerformance(vector<int>& calories, int k, int lower, int upper) {
        int n = calories.size();
        int s = accumulate(calories.begin(), calories.begin() + k, 0);
        int ans = 0;
        if (s < lower) --ans;
        else if (s > upper) ++ans;
        for (int i = k; i < n; ++i) {
            s += calories[i] - calories[i - k];
            if (s < lower) --ans;
            else if (s > upper) ++ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func dietPlanPerformance(calories []int, k int, lower int, upper int) int {
	n := len(calories)
	s := make([]int, n+1)
	for i, v := range calories {
		s[i+1] = s[i] + v
	}
	ans := 0
	for i := 0; i < n-k+1; i++ {
		t := s[i+k] - s[i]
		if t < lower {
			ans--
		} else if t > upper {
			ans++
		}
	}
	return ans
}
```

```go
func dietPlanPerformance(calories []int, k int, lower int, upper int) int {
	check := func(s int) int {
		if s < lower {
			return -1
		}
		if s > upper {
			return 1
		}
		return 0
	}
	n := len(calories)
	s := 0
	for i := 0; i < k; i++ {
		s += calories[i]
	}
	ans := check(s)
	for i := k; i < n; i++ {
		s += calories[i] - calories[i-k]
		ans += check(s)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
