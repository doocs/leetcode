# [1010. 总持续时间可被 60 整除的歌曲](https://leetcode.cn/problems/pairs-of-songs-with-total-durations-divisible-by-60)

[English Version](/solution/1000-1099/1010.Pairs%20of%20Songs%20With%20Total%20Durations%20Divisible%20by%2060/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在歌曲列表中，第 <code>i</code> 首歌曲的持续时间为 <code>time[i]</code> 秒。</p>

<p>返回其总持续时间（以秒为单位）可被 <code>60</code> 整除的歌曲对的数量。形式上，我们希望下标数字 <code>i</code> 和 <code>j</code> 满足&nbsp; <code>i &lt; j</code> 且有&nbsp;<code>(time[i] + time[j]) % 60 == 0</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>time = [30,20,150,100,40]
<strong>输出：</strong>3
<strong>解释：</strong>这三对的总持续时间可被 60 整除：
(time[0] = 30, time[2] = 150): 总持续时间 180
(time[1] = 20, time[3] = 100): 总持续时间 120
(time[1] = 20, time[4] = 40): 总持续时间 60
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>time = [60,60,60]
<strong>输出：</strong>3
<strong>解释：</strong>所有三对的总持续时间都是 120，可以被 60 整除。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= time.length &lt;= 6 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= time[i] &lt;= 500</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数 + 枚举**

我们可以用一个哈希表或数组 `cnt` 记录当前已经遍历过的歌曲的持续时间 `time[i]` 的数量。

枚举当前遍历到的歌曲的持续时间 `time[i]`，假设其为 `t`，那么我们只需要枚举 `60` 的倍数 `s`，并统计 `cnt[s - t]` 即可。然后将 `cnt[t]` 的值加 `1`。

时间复杂度 $O(n \times C)$，空间复杂度 $O(M)$。其中 $n$ 为数组 `time` 的长度，而 $C$ 和 $M$ 分别为数组 `time` 中的最大值以及 `60` 的倍数的个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numPairsDivisibleBy60(self, time: List[int]) -> int:
        cnt = defaultdict(int)
        ans = 0
        for t in time:
            s = 60
            for _ in range(17):
                ans += cnt[s - t]
                s += 60
            cnt[t] += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] cnt = new int[501];
        int ans = 0;
        for (int t : time) {
            int s = 60;
            for (int i = 0; i < 17; ++i) {
                if (s - t >= 0 && s - t < cnt.length) {
                    ans += cnt[s - t];
                }
                s += 60;
            }
            cnt[t]++;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numPairsDivisibleBy60(vector<int>& time) {
        int cnt[501]{};
        int ans = 0;
        for (int& t : time) {
            int s = 60;
            for (int i = 0; i < 17; ++i) {
                if (s - t >= 0 && s - t < 501) {
                    ans += cnt[s - t];
                }
                s += 60;
            }
            cnt[t]++;
        }
        return ans;
    }
};
```

### **Go**

```go
func numPairsDivisibleBy60(time []int) (ans int) {
	cnt := [501]int{}
	for _, t := range time {
		s := 60
		for i := 0; i < 17; i++ {
			if s-t >= 0 && s-t < 501 {
				ans += cnt[s-t]
			}
			s += 60
		}
		cnt[t]++
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
