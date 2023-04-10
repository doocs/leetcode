# [2611. 老鼠和奶酪](https://leetcode.cn/problems/mice-and-cheese)

[English Version](/solution/2600-2699/2611.Mice%20and%20Cheese/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有两只老鼠和&nbsp;<code>n</code>&nbsp;块不同类型的奶酪，每块奶酪都只能被其中一只老鼠吃掉。</p>

<p>下标为 <code>i</code>&nbsp;处的奶酪被吃掉的得分为：</p>

<ul>
	<li>如果第一只老鼠吃掉，则得分为&nbsp;<code>reward1[i]</code>&nbsp;。</li>
	<li>如果第二只老鼠吃掉，则得分为&nbsp;<code>reward2[i]</code>&nbsp;。</li>
</ul>

<p>给你一个正整数数组&nbsp;<code>reward1</code>&nbsp;，一个正整数数组&nbsp;<code>reward2</code>&nbsp;，和一个非负整数&nbsp;<code>k</code>&nbsp;。</p>

<p>请你返回第一只老鼠恰好吃掉 <code>k</code>&nbsp;块奶酪的情况下，<strong>最大</strong>&nbsp;得分为多少。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>reward1 = [1,1,3,4], reward2 = [4,4,1,1], k = 2
<b>输出：</b>15
<b>解释：</b>这个例子中，第一只老鼠吃掉第 2&nbsp;和 3 块奶酪（下标从 0 开始），第二只老鼠吃掉第 0 和 1 块奶酪。
总得分为 4 + 4 + 3 + 4 = 15 。
15 是最高得分。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>reward1 = [1,1], reward2 = [1,1], k = 2
<b>输出：</b>2
<b>解释：</b>这个例子中，第一只老鼠吃掉第 0 和 1 块奶酪（下标从 0 开始），第二只老鼠不吃任何奶酪。
总得分为 1 + 1 = 2 。
2 是最高得分。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == reward1.length == reward2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= reward1[i],&nbsp;reward2[i] &lt;= 1000</code></li>
	<li><code>0 &lt;= k &lt;= n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 排序**

我们可以先将所有奶酪分给第二只老鼠，接下来，考虑将其中 $k$ 块奶酪分给第一只老鼠，那么我们应该如何选择这 $k$ 块奶酪呢？显然，将第 $i$ 块奶酪从第二只老鼠分给第一只老鼠，得分的变化量为 $reward1[i] - reward2[i]$，我们希望这个变化量尽可能大，这样才能使得总得分最大。

因此，我们将奶酪按照 `reward1[i] - reward2[i]` 从大到小排序，前 $k$ 块奶酪由第一只老鼠吃掉，剩下的奶酪由第二只老鼠吃掉，即可得到最大得分。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为奶酪的数量。

相似题目：

-   [1029. 两地调度](/solution/1000-1099/1029.Two%20City%20Scheduling/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def miceAndCheese(self, reward1: List[int], reward2: List[int], k: int) -> int:
        n = len(reward1)
        idx = sorted(
            range(n), key=lambda i: reward1[i] - reward2[i], reverse=True)
        return sum(reward1[i] for i in idx[:k]) + sum(reward2[i] for i in idx[k:])
```

```python
class Solution:
    def miceAndCheese(self, reward1: List[int], reward2: List[int], k: int) -> int:
        for i, x in enumerate(reward2):
            reward1[i] -= x
        reward1.sort(reverse=True)
        return sum(reward2) + sum(reward1[:k])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int n = reward1.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> reward1[j] - reward2[j] - (reward1[i] - reward2[i]));
        int ans = 0;
        for (int i = 0; i < k; ++i) {
            ans += reward1[idx[i]];
        }
        for (int i = k; i < n; ++i) {
            ans += reward2[idx[i]];
        }
        return ans;
    }
}
```

```java
class Solution {
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int ans = 0;
        int n = reward1.length;
        for (int i = 0; i < n; ++i) {
            ans += reward2[i];
            reward1[i] -= reward2[i];
        }
        Arrays.sort(reward1);
        for (int i = 0; i < k; ++i) {
            ans += reward1[n - i - 1];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int miceAndCheese(vector<int>& reward1, vector<int>& reward2, int k) {
        int n = reward1.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) { return reward1[j] - reward2[j] < reward1[i] - reward2[i]; });
        int ans = 0;
        for (int i = 0; i < k; ++i) {
            ans += reward1[idx[i]];
        }
        for (int i = k; i < n; ++i) {
            ans += reward2[idx[i]];
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int miceAndCheese(vector<int>& reward1, vector<int>& reward2, int k) {
        int n = reward1.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += reward2[i];
            reward1[i] -= reward2[i];
        }
        sort(reward1.rbegin(), reward1.rend());
        ans += accumulate(reward1.begin(), reward1.begin() + k, 0);
        return ans;
    }
};
```

### **Go**

```go
func miceAndCheese(reward1 []int, reward2 []int, k int) (ans int) {
	n := len(reward1)
	idx := make([]int, n)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool {
		i, j = idx[i], idx[j]
		return reward1[j]-reward2[j] < reward1[i]-reward2[i]
	})
	for i := 0; i < k; i++ {
		ans += reward1[idx[i]]
	}
	for i := k; i < n; i++ {
		ans += reward2[idx[i]]
	}
	return
}
```

```go
func miceAndCheese(reward1 []int, reward2 []int, k int) (ans int) {
	for i, x := range reward2 {
		ans += x
		reward1[i] -= x
	}
	sort.Ints(reward1)
	n := len(reward1)
	for i := 0; i < k; i++ {
		ans += reward1[n-i-1]
	}
	return
}
```

### **TypeScript**

```ts
function miceAndCheese(
    reward1: number[],
    reward2: number[],
    k: number,
): number {
    const n = reward1.length;
    const idx = Array.from({ length: n }, (_, i) => i);
    idx.sort((i, j) => reward1[j] - reward2[j] - (reward1[i] - reward2[i]));
    let ans = 0;
    for (let i = 0; i < k; ++i) {
        ans += reward1[idx[i]];
    }
    for (let i = k; i < n; ++i) {
        ans += reward2[idx[i]];
    }
    return ans;
}
```

```ts
function miceAndCheese(
    reward1: number[],
    reward2: number[],
    k: number,
): number {
    const n = reward1.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans += reward2[i];
        reward1[i] -= reward2[i];
    }
    reward1.sort((a, b) => b - a);
    for (let i = 0; i < k; ++i) {
        ans += reward1[i];
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
