# [1711. 大餐计数](https://leetcode.cn/problems/count-good-meals)

[English Version](/solution/1700-1799/1711.Count%20Good%20Meals/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>大餐</strong> 是指 <strong>恰好包含两道不同餐品</strong> 的一餐，其美味程度之和等于 2 的幂。</p>

<p>你可以搭配 <strong>任意</strong> 两道餐品做一顿大餐。</p>

<p>给你一个整数数组 <code>deliciousness</code> ，其中 <code>deliciousness[i]</code> 是第 <code>i<sup>​​​​​​</sup>​​​​</code>​​​​ 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 <strong>大餐</strong> 的数量。结果需要对 <code>10<sup>9</sup> + 7</code> 取余。</p>

<p>注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>deliciousness = [1,3,5,7,9]
<strong>输出：</strong>4
<strong>解释：</strong>大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>deliciousness = [1,1,1,3,3,3,7]
<strong>输出：</strong>15
<strong>解释：</strong>大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= deliciousness.length <= 10<sup>5</sup></code></li>
	<li><code>0 <= deliciousness[i] <= 2<sup>20</sup></code></li>
</ul>

## 解法

**方法一：哈希表 + 枚举二的幂**

根据题意，我们需要统计数组中两个数的和为 $2$ 的幂的组合数。直接暴力枚举所有的组合数，时间复杂度为 $O(n^2)$ ，肯定会超时。

我们可以遍历数组，用哈希表 $cnt$ 维护数组中每个元素 $d$ 出现的次数。

对于每个元素，我们从小到大枚举二的幂次方 $s$ 作为两数之和，将哈希表中 $s - d$ 出现的次数累加到答案中。然后将当前元素 $d$ 出现的次数加一。

遍历结束后，返回答案即可。

时间复杂度 $O(n\times \log M)$，其中 $n$ 是数组 `deliciousness` 的长度，而 $M$ 是元素的上限，对于本题，上限 $M=2^{20}$。

我们也可以先用哈希表 $cnt$ 统计数组中每个元素出现的次数。

然后从小到大枚举二的幂次方 $s$ 作为两数之和，对于每个 $s$，遍历哈希表每个键值对 $(a, m)$，如果 $s - a$ 也在哈希表中，且 $s - a \neq a$，则答案加上 $m \times cnt[s - a]$；如果 $s - a = a$，则答案加上 $m \times (m - 1)$。

最后，将答案除以 $2$ 之后，模 $10^9 + 7$，返回即可。

时间复杂度与上面的方法相同。

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countPairs(self, deliciousness: List[int]) -> int:
        mod = 10**9 + 7
        mx = max(deliciousness) << 1
        cnt = Counter()
        ans = 0
        for d in deliciousness:
            s = 1
            while s <= mx:
                ans = (ans + cnt[s - d]) % mod
                s <<= 1
            cnt[d] += 1
        return ans
```

```python
class Solution:
    def countPairs(self, deliciousness: List[int]) -> int:
        mod = 10**9 + 7
        cnt = Counter(deliciousness)
        ans = 0
        for i in range(22):
            s = 1 << i
            for a, m in cnt.items():
                if (b := s - a) in cnt:
                    ans += m * (m - 1) if a == b else m * cnt[b]
        return (ans >> 1) % mod
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int countPairs(int[] deliciousness) {
        int mx = Arrays.stream(deliciousness).max().getAsInt() << 1;
        int ans = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int d : deliciousness) {
            for (int s = 1; s <= mx; s <<= 1) {
                ans = (ans + cnt.getOrDefault(s - d, 0)) % MOD;
            }
            cnt.merge(d, 1, Integer::sum);
        }
        return ans;
    }
}
```

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int countPairs(int[] deliciousness) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int d : deliciousness) {
            cnt.put(d, cnt.getOrDefault(d, 0) + 1);
        }
        long ans = 0;
        for (int i = 0; i < 22; ++i) {
            int s = 1 << i;
            for (var x : cnt.entrySet()) {
                int a = x.getKey(), m = x.getValue();
                int b = s - a;
                if (!cnt.containsKey(b)) {
                    continue;
                }
                ans += 1L * m * (a == b ? m - 1 : cnt.get(b));
            }
        }
        ans >>= 1;
        return (int) (ans % MOD);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int countPairs(vector<int>& deliciousness) {
        int mx = *max_element(deliciousness.begin(), deliciousness.end()) << 1;
        unordered_map<int, int> cnt;
        int ans = 0;
        for (auto& d : deliciousness) {
            for (int s = 1; s <= mx; s <<= 1) {
                ans = (ans + cnt[s - d]) % mod;
            }
            ++cnt[d];
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int countPairs(vector<int>& deliciousness) {
        unordered_map<int, int> cnt;
        for (int& d : deliciousness) ++cnt[d];
        long long ans = 0;
        for (int i = 0; i < 22; ++i) {
            int s = 1 << i;
            for (auto& [a, m] : cnt) {
                int b = s - a;
                if (!cnt.count(b)) continue;
                ans += 1ll * m * (a == b ? (m - 1) : cnt[b]);
            }
        }
        ans >>= 1;
        return ans % mod;
    }
};
```

### **Go**

```go
func countPairs(deliciousness []int) (ans int) {
	mx := 0
	for _, d := range deliciousness {
		mx = max(mx, d)
	}
	mx <<= 1
	const mod int = 1e9 + 7
	cnt := map[int]int{}
	for _, d := range deliciousness {
		for s := 1; s <= mx; s <<= 1 {
			ans = (ans + cnt[s-d]) % mod
		}
		cnt[d]++
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func countPairs(deliciousness []int) (ans int) {
    cnt := map[int]int{}
    for _, d := range deliciousness {
        cnt[d]++
    }
    const mod int = 1e9 + 7
    for i := 0; i < 22; i++ {
        s := 1 << i
        for a, m := range cnt {
            b := s - a
            if n, ok := cnt[b]; ok {
                if a == b {
                    ans += m * (m - 1)
                } else {
                    ans += m * n
                }
            }
        }
    }
    ans >>= 1
    return ans % mod
}
```

### **...**

```

```

<!-- tabs:end -->
