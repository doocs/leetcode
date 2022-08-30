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

**方法一：哈希表**

用最暴力的方法枚举每对元素肯定会超时，可以用哈希表优化对**之前元素出现次数**的查询。

**方法二：枚举二的幂**

用哈希表 `cnt` 记录数组中每个元素出现的次数。

枚举二的幂次方作为两数之和 `s`，然后枚举其中一个数 `a`，判断 `s-a` 是否出现在哈希表 `cnt` 中。若出现，判断 `a` 与 `b` 是否相等，是则答案累加 `cnt[a] * (cnt[a]-1)`，否则答案累加 `cnt[a] * cnt[b]`。

由于每个 `a`，`b` 会重复枚举，因此最后答案需要除以 `2`。注意取模操作。

时间复杂度 $O(n\log C)$，其中 $n$ 是数组 `deliciousness` 的长度，而 $C$ 是元素的上限，对于本题，上限 $C=2^{20}$。

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countPairs(self, deliciousness: List[int]) -> int:
        mod = 1000000007
        limit = max(deliciousness) * 2
        pairs = 0
        freq = defaultdict(int)
        for d in deliciousness:
            target = 1
            while target <= limit:
                pairs = (pairs + freq[target - d]) % mod
                target = target << 1
            freq[d] += 1
        return pairs
```

```python
class Solution:
    def countPairs(self, deliciousness: List[int]) -> int:
        cnt = Counter(deliciousness)
        ans = 0
        mod = 10**9 + 7
        for i in range(22):
            s = 1 << i
            for a, m in cnt.items():
                if (b := s - a) in cnt:
                    if a == b:
                        ans += m * (m - 1)
                    else:
                        ans += m * cnt[b]
        return (ans >> 1) % mod
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    private static final int MOD = 1000000007;

    public int countPairs(int[] deliciousness) {
        int limit = Arrays.stream(deliciousness).max().getAsInt() * 2;
        int pairs = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int d : deliciousness) {
            for (int sum = 1; sum <= limit; sum <<= 1) {
                int count = freq.getOrDefault(sum - d, 0);
                pairs = (pairs + count) % MOD;
            }
            freq.merge(d, 1, Integer::sum);
        }
        return pairs;
    }
}
```

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int countPairs(int[] deliciousness) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int v : deliciousness) {
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
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
                if (a == b) {
                    ans += (long) m * (m - 1);
                } else {
                    ans += (long) m * cnt.get(b);
                }
            }
        }
        ans >>= 1;
        return (int) (ans % MOD);
    }
}
```

### **Go**

```go
const mod int = 1e9 + 7

func countPairs(deliciousness []int) int {
	limit := 0
	for _, d := range deliciousness {
		limit = max(limit, d)
	}
	limit *= 2
	pairs := 0
	freq := make(map[int]int)
	for _, d := range deliciousness {
		for sum := 1; sum <= limit; sum <<= 1 {
			pairs = (pairs + freq[sum-d]) % mod
		}
		freq[d]++
	}
	return pairs
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}
```

```go
func countPairs(deliciousness []int) int {
	cnt := map[int]int{}
	for _, v := range deliciousness {
		cnt[v]++
	}
	ans := 0
	mod := int(1e9) + 7
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

### **C++**

```cpp
class Solution {
public:
    int countPairs(vector<int>& deliciousness) {
        unordered_map<int, int> cnt;
        for (int v : deliciousness) ++cnt[v];
        long long ans = 0;
        for (int i = 0; i < 22; ++i) {
            int s = 1 << i;
            for (auto& [a, m] : cnt) {
                int b = s - a;
                if (!cnt.count(b)) continue;
                if (a == b) ans += 1ll * m * (m - 1);
                else ans += 1ll * m * cnt[b];
            }
        }
        ans >>= 1;
        int mod = 1e9 + 7;
        return (int) (ans % mod);
    }
};
```

### **...**

```

```

<!-- tabs:end -->
