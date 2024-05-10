# [2391. 收集垃圾的最少总时间](https://leetcode.cn/problems/minimum-amount-of-time-to-collect-garbage)

[English Version](/solution/2300-2399/2391.Minimum%20Amount%20of%20Time%20to%20Collect%20Garbage/README_EN.md)

<!-- tags:数组,字符串,前缀和 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的字符串数组&nbsp;<code>garbage</code>&nbsp;，其中&nbsp;<code>garbage[i]</code>&nbsp;表示第 <code>i</code>&nbsp;个房子的垃圾集合。<code>garbage[i]</code>&nbsp;只包含字符&nbsp;<code>'M'</code>&nbsp;，<code>'P'</code> 和&nbsp;<code>'G'</code>&nbsp;，但可能包含多个相同字符，每个字符分别表示一单位的金属、纸和玻璃。垃圾车收拾 <strong>一</strong>&nbsp;单位的任何一种垃圾都需要花费&nbsp;<code>1</code>&nbsp;分钟。</p>

<p>同时给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>travel</code>&nbsp;，其中&nbsp;<code>travel[i]</code>&nbsp;是垃圾车从房子 <code>i</code>&nbsp;行驶到房子 <code>i + 1</code>&nbsp;需要的分钟数。</p>

<p>城市里总共有三辆垃圾车，分别收拾三种垃圾。每辆垃圾车都从房子 <code>0</code>&nbsp;出发，<strong>按顺序</strong>&nbsp;到达每一栋房子。但它们 <strong>不是必须</strong>&nbsp;到达所有的房子。</p>

<p>任何时刻只有 <strong>一辆</strong>&nbsp;垃圾车处在使用状态。当一辆垃圾车在行驶或者收拾垃圾的时候，另外两辆车 <strong>不能</strong>&nbsp;做任何事情。</p>

<p>请你返回收拾完所有垃圾需要花费的 <strong>最少</strong>&nbsp;总分钟数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>garbage = ["G","P","GP","GG"], travel = [2,4,3]
<b>输出：</b>21
<strong>解释：</strong>
收拾纸的垃圾车：
1. 从房子 0 行驶到房子 1
2. 收拾房子 1 的纸垃圾
3. 从房子 1 行驶到房子 2
4. 收拾房子 2 的纸垃圾
收拾纸的垃圾车总共花费 8 分钟收拾完所有的纸垃圾。
收拾玻璃的垃圾车：
1. 收拾房子 0 的玻璃垃圾
2. 从房子 0 行驶到房子 1
3. 从房子 1 行驶到房子 2
4. 收拾房子 2 的玻璃垃圾
5. 从房子 2 行驶到房子 3
6. 收拾房子 3 的玻璃垃圾
收拾玻璃的垃圾车总共花费 13 分钟收拾完所有的玻璃垃圾。
由于没有金属垃圾，收拾金属的垃圾车不需要花费任何时间。
所以总共花费 8 + 13 = 21 分钟收拾完所有垃圾。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>garbage = ["MMM","PGM","GP"], travel = [3,10]
<b>输出：</b>37
<strong>解释：</strong>
收拾金属的垃圾车花费 7 分钟收拾完所有的金属垃圾。
收拾纸的垃圾车花费 15 分钟收拾完所有的纸垃圾。
收拾玻璃的垃圾车花费 15 分钟收拾完所有的玻璃垃圾。
总共花费 7 + 15 + 15 = 37 分钟收拾完所有的垃圾。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= garbage.length &lt;= 10<sup>5</sup></code></li>
	<li><code>garbage[i]</code> 只包含字母&nbsp;<code>'M'</code>&nbsp;，<code>'P'</code>&nbsp;和&nbsp;<code>'G'</code>&nbsp;。</li>
	<li><code>1 &lt;= garbage[i].length &lt;= 10</code></li>
	<li><code>travel.length == garbage.length - 1</code></li>
	<li><code>1 &lt;= travel[i] &lt;= 100</code></li>
</ul>

## 解法

### 方法一：哈希表 + 前缀和

根据题目描述，每一辆垃圾车从房子 $0$ 出发，收集其中一种垃圾，按顺序前进，直到到达该种垃圾最后出现的房子下标为止。

因此，我们可以用一个哈希表 $\text{last}$ 记录每种垃圾最后出现的房子下标。我们假设第 $i$ 种垃圾最后一次出现在第 $j$ 个房子，那么第 $i$ 辆车所需要的行驶时间为 $\text{travel}[0] + \text{travel}[1] + \cdots + \text{travel}[j-1]$。注意，如果 $j = 0$，则不需要行驶时间。我们通过前缀和累计所有车辆的行驶时间，加上每种垃圾的总收集时间，即可得到答案。

时间复杂度 $O(n)$，空间复杂度 $O(k)$，其中 $n$ 和 $k$ 分别是垃圾的数量和种类。本题中 $k = 3$。

<!-- tabs:start -->

```python
class Solution:
    def garbageCollection(self, garbage: List[str], travel: List[int]) -> int:
        last = {}
        ans = 0
        for i, s in enumerate(garbage):
            ans += len(s)
            for c in s:
                last[c] = i
        ts = 0
        for i, t in enumerate(travel, 1):
            ts += t
            ans += sum(ts for j in last.values() if i == j)
        return ans
```

```java
class Solution {
    public int garbageCollection(String[] garbage, int[] travel) {
        Map<Character, Integer> last = new HashMap<>(3);
        int ans = 0;
        for (int i = 0; i < garbage.length; ++i) {
            String s = garbage[i];
            ans += s.length();
            for (char c : s.toCharArray()) {
                last.put(c, i);
            }
        }
        int ts = 0;
        for (int i = 1; i <= travel.length; ++i) {
            ts += travel[i - 1];
            for (int j : last.values()) {
                if (i == j) {
                    ans += ts;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int garbageCollection(vector<string>& garbage, vector<int>& travel) {
        unordered_map<char, int> last;
        int ans = 0;
        for (int i = 0; i < garbage.size(); ++i) {
            auto& s = garbage[i];
            ans += s.size();
            for (char& c : s) {
                last[c] = i;
            }
        }
        int ts = 0;
        for (int i = 1; i <= travel.size(); ++i) {
            ts += travel[i - 1];
            for (auto& [_, j] : last) {
                if (i == j) {
                    ans += ts;
                }
            }
        }
        return ans;
    }
};
```

```go
func garbageCollection(garbage []string, travel []int) (ans int) {
	last := map[byte]int{}
	for i, s := range garbage {
		ans += len(s)
		for j := range s {
			last[s[j]] = i
		}
	}
	ts := 0
	for i := 1; i <= len(travel); i++ {
		ts += travel[i-1]
		for _, j := range last {
			if i == j {
				ans += ts
			}
		}
	}
	return
}
```

```ts
function garbageCollection(garbage: string[], travel: number[]): number {
    const last: Map<string, number> = new Map();
    let ans = 0;
    for (let i = 0; i < garbage.length; ++i) {
        const s = garbage[i];
        ans += s.length;
        for (const c of s) {
            last.set(c, i);
        }
    }
    let ts = 0;
    for (let i = 1; i <= travel.length; ++i) {
        ts += travel[i - 1];
        for (const [_, j] of last) {
            if (i === j) {
                ans += ts;
            }
        }
    }
    return ans;
}
```

```rust
use std::collections::HashMap;

impl Solution {
    pub fn garbage_collection(garbage: Vec<String>, travel: Vec<i32>) -> i32 {
        let mut last: HashMap<char, usize> = HashMap::new();
        let mut ans = 0;
        for (i, s) in garbage.iter().enumerate() {
            ans += s.len() as i32;
            for c in s.chars() {
                last.insert(c, i);
            }
        }
        let mut ts = 0;
        for (i, t) in travel.iter().enumerate() {
            ts += t;
            for &j in last.values() {
                if i + 1 == j {
                    ans += ts;
                }
            }
        }
        ans
    }
}
```

```cs
public class Solution {
    public int GarbageCollection(string[] garbage, int[] travel) {
        Dictionary<char, int> last = new Dictionary<char, int>();
        int ans = 0;
        for (int i = 0; i < garbage.Length; ++i) {
            ans += garbage[i].Length;
            foreach (char c in garbage[i]) {
                last[c] = i;
            }
        }
        int ts = 0;
        for (int i = 1; i <= travel.Length; ++i) {
            ts += travel[i - 1];
            foreach (int j in last.Values) {
                if (i == j) {
                    ans += ts;
                }
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- end -->
