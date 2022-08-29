# [2391. 收集垃圾的最少总时间](https://leetcode.cn/problems/minimum-amount-of-time-to-collect-garbage)

[English Version](/solution/2300-2399/2391.Minimum%20Amount%20of%20Time%20to%20Collect%20Garbage/README_EN.md)

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

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数统计**

由于题目中说明同一时刻只有一辆车处于使用状态，因此我们直接模拟每辆车的运行过程，累加时间。

更进一步思考，我们发现，答案的总耗时其实可以分成两部分：

1. 所有垃圾的数量，我们遍历 `garbage` 中的每一项 `v`，然后累加 `v.length` 就能得到；
1. 根据每一种垃圾在 `garbage` 中最后一次出现的位置 `i`，我们累加 `travel[0..i)` 即可。这里可以先算出 `travel` 的前缀和。

时间复杂度 $O(n)$，其中 $n$ 为垃圾的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def garbageCollection(self, garbage: List[str], travel: List[int]) -> int:
        def f(c):
            tot = sum(v.count(c) for v in garbage)
            res = 0
            for i, v in enumerate(garbage):
                t = v.count(c)
                res += t
                tot -= t
                if tot == 0:
                    break
                if i < n - 1:
                    res += travel[i]
            return res

        n = len(garbage)
        return f('M') + f('P') + f('G')
```

```python
class Solution:
    def garbageCollection(self, garbage: List[str], travel: List[int]) -> int:
        ans = 0
        pos = {}
        for i, v in enumerate(garbage):
            ans += len(v)
            for c in v:
                pos[c] = i
        s = list(accumulate(travel, initial=0))
        ans += sum(s[i] for i in pos.values())
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private String[] g;
    private int[] travel;

    public int garbageCollection(String[] garbage, int[] travel) {
        g = garbage;
        this.travel = travel;
        return f('M') + f('P') + f('G');
    }

    private int f(char c) {
        int tot = 0;
        for (var v : g) {
            for (char ch : v.toCharArray()) {
                if (ch == c) {
                    ++tot;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < g.length; ++i) {
            int t = 0;
            for (char ch : g[i].toCharArray()) {
                if (ch == c) {
                    ++t;
                }
            }
            res += t;
            tot -= t;
            if (tot == 0) {
                break;
            }
            if (i < g.length - 1) {
                res += travel[i];
            }
        }
        return res;
    }
}
```

```java
class Solution {
    public int garbageCollection(String[] garbage, int[] travel) {
        int ans = 0;
        int[] pos = new int[26];
        for (int i = 0; i < garbage.length; ++i) {
            ans += garbage[i].length();
            for (char c : garbage[i].toCharArray()) {
                pos[c - 'A'] = i;
            }
        }
        int[] s = new int[travel.length + 1];
        for (int i = 0; i < travel.length; ++i) {
            s[i + 1] = s[i] + travel[i];
        }
        for (int i : pos) {
            ans += s[i];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> g;
    vector<int> travel;

    int garbageCollection(vector<string>& garbage, vector<int>& travel) {
        g = garbage;
        this->travel = travel;
        return f('M') + f('P') + f('G');
    }

    int f(char c) {
        int tot = 0;
        for (string& v : g) {
            for (char ch : v) {
                tot += ch == c;
            }
        }
        int res = 0;
        for (int i = 0; i < g.size(); ++i) {
            int t = 0;
            for (char ch : g[i]) {
                t += ch == c;
            }
            res += t;
            tot -= t;
            if (tot == 0) break;
            if (i < g.size() - 1) res += travel[i];
        }
        return res;
    }
};
```

```cpp
class Solution {
public:
    int garbageCollection(vector<string>& garbage, vector<int>& travel) {
        int ans = 0;
        vector<int> pos(26);
        for (int i = 0; i < garbage.size(); ++i) {
            ans += garbage[i].size();
            for (char c : garbage[i]) {
                pos[c - 'A'] = i;
            }
        }
        vector<int> s(travel.size() + 1);
        for (int i = 0; i < travel.size(); ++i) {
            s[i + 1] = s[i] + travel[i];
        }
        for (int i : pos) {
            ans += s[i];
        }
        return ans;
    }
};
```

### **Go**

```go
func garbageCollection(garbage []string, travel []int) int {
	n := len(garbage)
	f := func(c rune) int {
		tot := 0
		for _, v := range garbage {
			for _, ch := range v {
				if ch == c {
					tot++
				}
			}
		}
		res := 0
		for i, v := range garbage {
			t := 0
			for _, ch := range v {
				if ch == c {
					t++
				}
			}
			res += t
			tot -= t
			if tot == 0 {
				break
			}
			if i < n-1 {
				res += travel[i]
			}
		}
		return res
	}
	return f('M') + f('P') + f('G')
}
```

```go
func garbageCollection(garbage []string, travel []int) int {
	ans := 0
	pos := map[rune]int{}
	for i, v := range garbage {
		ans += len(v)
		for _, c := range v {
			pos[c] = i
		}
	}
	s := make([]int, len(travel)+1)
	for i, v := range travel {
		s[i+1] = s[i] + v
	}
	for _, i := range pos {
		ans += s[i]
	}
	return ans
}
```

### **TypeScript**

```ts
function garbageCollection(garbage: string[], travel: number[]): number {
    const n = garbage.length;
    const count = [0, 0, 0];
    const cs = ['G', 'P', 'M'];
    for (const s of garbage) {
        for (const c of s) {
            if (c === 'G') {
                count[0]++;
            } else if (c === 'P') {
                count[1]++;
            } else if (c === 'M') {
                count[2]++;
            }
        }
    }

    let res = 0;
    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < n; j++) {
            const s = garbage[j];
            for (const c of s) {
                if (c === cs[i]) {
                    res++;
                    count[i]--;
                }
            }
            if (count[i] === 0) {
                break;
            }
            res += travel[j];
        }
    }
    return res;
}
```

```ts
function garbageCollection(garbage: string[], travel: number[]): number {
    let ans = 0;
    let pos = new Map();
    for (let i = 0; i < garbage.length; ++i) {
        ans += garbage[i].length;
        for (const c of garbage[i]) {
            pos.set(c, i);
        }
    }
    let s = new Array(travel.length + 1).fill(0);
    for (let i = 0; i < travel.length; ++i) {
        s[i + 1] = s[i] + travel[i];
    }
    for (const [_, i] of pos) {
        ans += s[i];
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn garbage_collection(garbage: Vec<String>, travel: Vec<i32>) -> i32 {
        let n = garbage.len();
        let cs = [b'M', b'P', b'G'];
        let mut count = [0, 0, 0];
        for s in garbage.iter() {
            for c in s.as_bytes().iter() {
                count[if c == &b'M' {
                    0
                } else if c == &b'P' {
                    1
                } else {
                    2
                }] += 1;
            }
        }

        let mut res = 0;
        for i in 0..3 {
            for j in 0..n {
                let s = &garbage[j];
                for c in s.as_bytes().iter() {
                    if c == &cs[i] {
                        res += 1;
                        count[i] -= 1;
                    }
                }
                if count[i] == 0 {
                    break;
                }

                res += travel[j];
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
