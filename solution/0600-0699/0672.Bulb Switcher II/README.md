# [672. 灯泡开关 Ⅱ](https://leetcode.cn/problems/bulb-switcher-ii)

[English Version](/solution/0600-0699/0672.Bulb%20Switcher%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现有一个房间，墙上挂有&nbsp;<code>n</code>&nbsp;只已经打开的灯泡和 4 个按钮。在进行了&nbsp;<code>m</code>&nbsp;次未知操作后，你需要返回这&nbsp;<code>n</code>&nbsp;只灯泡可能有多少种不同的状态。</p>

<p>假设这 <code>n</code> 只灯泡被编号为 [1, 2, 3 ..., n]，这 4 个按钮的功能如下：</p>

<ol>
	<li>将所有灯泡的状态反转（即开变为关，关变为开）</li>
	<li>将编号为偶数的灯泡的状态反转</li>
	<li>将编号为奇数的灯泡的状态反转</li>
	<li>将编号为 <code>3k+1</code> 的灯泡的状态反转（k = 0, 1, 2, ...)</li>
</ol>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> n = 1, m = 1.
<strong>输出:</strong> 2
<strong>说明:</strong> 状态为: [开], [关]
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> n = 2, m = 1.
<strong>输出:</strong> 3
<strong>说明:</strong> 状态为: [开, 关], [关, 开], [关, 关]
</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入:</strong> n = 3, m = 1.
<strong>输出:</strong> 4
<strong>说明:</strong> 状态为: [关, 开, 关], [开, 关, 开], [关, 关, 关], [关, 开, 开].
</pre>

<p><strong>注意：</strong>&nbsp;<code>n</code>&nbsp;和&nbsp;<code>m</code> 都属于 [0, 1000].</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：位运算**

观察灯泡开关随按钮操作的变化规律，我们可以发现，位置 $i$ 与 $i+6$ 的灯泡，开关状态始终保持一致，因此，我们只需要考虑最多前 $n=6$ 个灯泡的开关状态。

另外，对于每个按钮，若操作偶数次，相当于没执行操作；若操作奇数次，相当于操作了 $1$ 次。同时，不同按钮操作的先后顺序，也不影响结果。

题目有 $4$ 个按钮，每个按钮有“操作偶数次”和“操作奇数次”两种状态，因此总共有 $2^4$ 种按钮状态。

二进制枚举按钮的状态 `mask`，若当前状态满足题目 `presses` 的限制，我们可以通过位运算，模拟操作对应按钮，最终得到灯泡的状态 $t$，去重后的 $t$ 的数量就是答案。

时空复杂度均为常数级别。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def flipLights(self, n: int, presses: int) -> int:
        ops = (0b111111, 0b010101, 0b101010, 0b100100)
        n = min(n, 6)
        vis = set()
        for mask in range(1 << 4):
            cnt = mask.bit_count()
            if cnt <= presses and cnt % 2 == presses % 2:
                t = 0
                for i, op in enumerate(ops):
                    if (mask >> i) & 1:
                        t ^= op
                t &= (1 << 6) - 1
                t >>= 6 - n
                vis.add(t)
        return len(vis)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int flipLights(int n, int presses) {
        int[] ops = new int[] {0b111111, 0b010101, 0b101010, 0b100100};
        Set<Integer> vis = new HashSet<>();
        n = Math.min(n, 6);
        for (int mask = 0; mask < 1 << 4; ++mask) {
            int cnt = Integer.bitCount(mask);
            if (cnt <= presses && cnt % 2 == presses % 2) {
                int t = 0;
                for (int i = 0; i < 4; ++i) {
                    if (((mask >> i) & 1) == 1) {
                        t ^= ops[i];
                    }
                }
                t &= ((1 << 6) - 1);
                t >>= (6 - n);
                vis.add(t);
            }
        }
        return vis.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int flipLights(int n, int presses) {
        n = min(n, 6);
        vector<int> ops = {0b111111, 0b010101, 0b101010, 0b100100};
        unordered_set<int> vis;
        for (int mask = 0; mask < 1 << 4; ++mask) {
            int cnt = __builtin_popcount(mask);
            if (cnt > presses || cnt % 2 != presses % 2) continue;
            int t = 0;
            for (int i = 0; i < 4; ++i) {
                if (mask >> i & 1) {
                    t ^= ops[i];
                }
            }
            t &= (1 << 6) - 1;
            t >>= (6 - n);
            vis.insert(t);
        }
        return vis.size();
    }
};
```

### **Go**

```go
func flipLights(n int, presses int) int {
	if n > 6 {
		n = 6
	}
	ops := []int{0b111111, 0b010101, 0b101010, 0b100100}
	vis := map[int]bool{}
	for mask := 0; mask < 1<<4; mask++ {
		cnt := bits.OnesCount(uint(mask))
		if cnt <= presses && cnt%2 == presses%2 {
			t := 0
			for i, op := range ops {
				if mask>>i&1 == 1 {
					t ^= op
				}
			}
			t &= 1<<6 - 1
			t >>= (6 - n)
			vis[t] = true
		}
	}
	return len(vis)
}
```

### **...**

```

```

<!-- tabs:end -->
