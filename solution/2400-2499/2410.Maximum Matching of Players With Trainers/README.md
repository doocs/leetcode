# [2410. 运动员和训练师的最大匹配数](https://leetcode.cn/problems/maximum-matching-of-players-with-trainers)

[English Version](/solution/2400-2499/2410.Maximum%20Matching%20of%20Players%20With%20Trainers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>players</code>&nbsp;，其中&nbsp;<code>players[i]</code>&nbsp;表示第 <code>i</code>&nbsp;名运动员的 <strong>能力</strong>&nbsp;值，同时给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>trainers</code>&nbsp;，其中&nbsp;<code>trainers[j]</code>&nbsp;表示第 <code>j</code>&nbsp;名训练师的 <strong>训练能力值</strong>&nbsp;。</p>

<p>如果第 <code>i</code>&nbsp;名运动员的能力值 <strong>小于等于</strong>&nbsp;第 <code>j</code>&nbsp;名训练师的能力值，那么第&nbsp;<code>i</code>&nbsp;名运动员可以 <strong>匹配</strong>&nbsp;第&nbsp;<code>j</code>&nbsp;名训练师。除此以外，每名运动员至多可以匹配一位训练师，每位训练师最多可以匹配一位运动员。</p>

<p>请你返回满足上述要求&nbsp;<code>players</code>&nbsp;和 <code>trainers</code>&nbsp;的 <strong>最大</strong> 匹配数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>players = [4,7,9], trainers = [8,2,5,8]
<b>输出：</b>2
<b>解释：</b>
得到两个匹配的一种方案是：
- players[0] 与 trainers[0] 匹配，因为 4 &lt;= 8 。
- players[1] 与 trainers[3] 匹配，因为 7 &lt;= 8 。
可以证明 2 是可以形成的最大匹配数。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>players = [1,1,1], trainers = [10]
<b>输出：</b>1
<b>解释：</b>
训练师可以匹配所有 3 个运动员
每个运动员至多只能匹配一个训练师，所以最大答案是 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= players.length, trainers.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= players[i], trainers[j] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 双指针**

按运动员的能力值从小到大排序，选择大于等于运动员能力值的，且自身能力值最小的训练师。

时间复杂度 $O(n\log n + m\log m)$。其中 $n$, $m$ 分别为运动员和训练师的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def matchPlayersAndTrainers(self, players: List[int], trainers: List[int]) -> int:
        players.sort()
        trainers.sort()
        ans = j = 0
        for p in players:
            while j < len(trainers) and trainers[j] < p:
                j += 1
            if j < len(trainers):
                ans += 1
                j += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int ans = 0;
        int j = 0;
        for (int p : players) {
            while (j < trainers.length && trainers[j] < p) {
                ++j;
            }
            if (j < trainers.length) {
                ++ans;
                ++j;
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
    int matchPlayersAndTrainers(vector<int>& players, vector<int>& trainers) {
        sort(players.begin(), players.end());
        sort(trainers.begin(), trainers.end());
        int ans = 0, j = 0;
        for (int p : players) {
            while (j < trainers.size() && trainers[j] < p) {
                ++j;
            }
            if (j < trainers.size()) {
                ++ans;
                ++j;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func matchPlayersAndTrainers(players []int, trainers []int) int {
	sort.Ints(players)
	sort.Ints(trainers)
	ans, j := 0, 0
	for _, p := range players {
		for j < len(trainers) && trainers[j] < p {
			j++
		}
		if j < len(trainers) {
			ans++
			j++
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
