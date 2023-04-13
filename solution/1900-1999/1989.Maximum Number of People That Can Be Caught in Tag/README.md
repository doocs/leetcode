# [1989. 捉迷藏中可捕获的最大人数](https://leetcode.cn/problems/maximum-number-of-people-that-can-be-caught-in-tag)

[English Version](/solution/1900-1999/1989.Maximum%20Number%20of%20People%20That%20Can%20Be%20Caught%20in%20Tag/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你正在和你的朋友玩捉迷藏游戏。在捉迷藏比赛中，人们被分成两组：是 “鬼” 的人，和不是 “鬼” 的人。是 “鬼” 的人想要抓住尽可能多的不是 “鬼” 的人。</p>

<p>给定一个 <strong>从 0 开始建立索引</strong> 的整数数组 <code>team</code>，其中只包含 0 (表示&nbsp;<strong>不是</strong> “鬼” 的人) 和 1 (表示是 “鬼” 的人)，以及一个整数 <code>dist</code>。索引 <code>i</code> 为 “鬼” 的人可以捕获索引在 <code>[i - dist, i + dist]</code>(<strong>包括</strong>) 范围内且 <strong>不是</strong> “鬼” 的任何<strong>一个</strong>人。</p>

<p>返回 <em>“鬼” 所能捕获的最大人数</em>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入:</strong> team = [0,1,0,1,0], dist = 3
<strong>输出:</strong> 2
<strong>解释:</strong>
在索引 1 的 “鬼” 可以捕获范围 [i-dist, i+dist] = [1-3, 1+3] = [-2, 4] 内的人。
他们可以抓住索引 2 中不是 “鬼” 的人。
在索引 3 的 “鬼” 可以捕获范围 [i-dist, i+dist] = [3-3, 3+3] = [0, 6] 内的人。
他们可以抓住索引 0 中不是 “鬼” 的人。
在索引 4 上不是 “鬼” 的人不会被抓住，因为在索引 1 和 3 上的人已经抓住了一个人。</pre>

<p><strong class="example">示例 2:</strong></p>

<pre>
<strong>输入:</strong> team = [1], dist = 1
<strong>输出:</strong> 0
<strong>解释:</strong>
没有 “鬼" 要抓的人。
</pre>

<p><strong class="example">示例 3:</strong></p>

<pre>
<strong>输入:</strong> team = [0], dist = 1
<strong>输出:</strong> 0
<strong>解释:
</strong>没有 “鬼” 来抓人。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= team.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= team[i] &lt;= 1</code></li>
	<li><code>1 &lt;= dist &lt;= team.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

我们可以用两个指针 $i$ 和 $j$ 指向鬼和非鬼的人，初始时 $i=0$, $j=0$。

然后我们从左到右遍历数组，当遇到鬼时，即 $team[i]=1$ 时，如果此时 $j \lt n$ 并且 $team[j]=1$ 或者 $i - j \gt dist$，则指针 $j$ 循环右移，也即是说，我们要找到第一个不是鬼的人，且 $i$ 和 $j$ 之间的距离不超过 $dist$。如果找到了这样的人，则将指针 $j$ 右移一位，表示我们已经抓住了这个人，同时答案加一。继续遍历数组，直到遍历完整个数组。

最后返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def catchMaximumAmountofPeople(self, team: List[int], dist: int) -> int:
        ans = j = 0
        n = len(team)
        for i, x in enumerate(team):
            if x:
                while j < n and (team[j] or i - j > dist):
                    j += 1
                if j < n and abs(i - j) <= dist:
                    ans += 1
                    j += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int catchMaximumAmountofPeople(int[] team, int dist) {
        int ans = 0;
        int n = team.length;
        for (int i = 0, j = 0; i < n; ++i) {
            if (team[i] == 1) {
                while (j < n && (team[j] == 1 || i - j > dist)) {
                    ++j;
                }
                if (j < n && Math.abs(i - j) <= dist) {
                    ++ans;
                    ++j;
                }
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
    int catchMaximumAmountofPeople(vector<int>& team, int dist) {
        int ans = 0;
        int n = team.size();
        for (int i = 0, j = 0; i < n; ++i) {
            if (team[i]) {
                while (j < n && (team[j] || i - j > dist)) {
                    ++j;
                }
                if (j < n && abs(i -  j) <= dist) {
                    ++ans;
                    ++j;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func catchMaximumAmountofPeople(team []int, dist int) (ans int) {
	n := len(team)
	for i, j := 0, 0; i < n; i++ {
		if team[i] == 1 {
			for j < n && (team[j] == 1 || i-j > dist) {
				j++
			}
			if j < n && abs(i-j) <= dist {
				ans++
				j++
			}
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
