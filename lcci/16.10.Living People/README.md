# [面试题 16.10. 生存人数](https://leetcode.cn/problems/living-people-lcci)

[English Version](/lcci/16.10.Living%20People/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定N个人的出生年份和死亡年份，第<code>i</code>个人的出生年份为<code>birth[i]</code>，死亡年份为<code>death[i]</code>，实现一个方法以计算生存人数最多的年份。</p>
<p>你可以假设所有人都出生于1900年至2000年（含1900和2000）之间。如果一个人在某一年的任意时期都处于生存状态，那么他们应该被纳入那一年的统计中。例如，生于1908年、死于1909年的人应当被列入1908年和1909年的计数。</p>
<p>如果有多个年份生存人数相同且均为最大值，输出其中最小的年份。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong>
birth = {1900, 1901, 1950}
death = {1948, 1951, 2000}
<strong>输出：</strong> 1901
</pre>
<p><strong>提示：</strong></p>
<ul>
<li><code>0 < birth.length == death.length <= 10000</code></li>
<li><code>birth[i] <= death[i]</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：差分数组**

题目实际上是对一个连续的区间进行加减操作，然后求最大值。这种情况下可以使用差分数组来解决。

由于题目中的年份范围是固定的，所以可以使用一个长度为 $102$ 的数组来表示 $1900$ 年到 $2000$ 年的人口变化情况。数组中的每个元素表示该年份的人口变化，正数表示出生人数，负数表示死亡人数。

遍历每个人的出生年份和死亡年份，对应的年份的人口变化加一和减一。然后遍历差分数组，求出差分数组的前缀和的最大值，最大值对应的年份即为答案。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 是出生年份和死亡年份的长度，而 $C$ 是年份的范围。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxAliveYear(self, birth: List[int], death: List[int]) -> int:
        base = 1900
        d = [0] * 102
        for a, b in zip(birth, death):
            d[a - base] += 1
            d[b + 1 - base] -= 1
        s = mx = 0
        ans = 0
        for i, x in enumerate(d):
            s += x
            if mx < s:
                mx = s
                ans = base + i
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxAliveYear(int[] birth, int[] death) {
        int base = 1900;
        int[] d = new int[102];
        for (int i = 0; i < birth.length; ++i) {
            int a = birth[i] - base;
            int b = death[i] - base;
            ++d[a];
            --d[b + 1];
        }
        int s = 0, mx = 0;
        int ans = 0;
        for (int i = 0; i < d.length; ++i) {
            s += d[i];
            if (mx < s) {
                mx = s;
                ans = base + i;
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
    int maxAliveYear(vector<int>& birth, vector<int>& death) {
        int base = 1900;
        int d[102]{};
        for (int i = 0; i < birth.size(); ++i) {
            int a = birth[i] - base;
            int b = death[i] - base;
            ++d[a];
            --d[b + 1];
        }
        int s = 0, mx = 0;
        int ans = 0;
        for (int i = 0; i < 102; ++i) {
            s += d[i];
            if (mx < s) {
                mx = s;
                ans = base + i;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxAliveYear(birth []int, death []int) (ans int) {
	base := 1900
	d := [102]int{}
	for i, a := range birth {
		a -= base
		b := death[i] - base
		d[a]++
		d[b+1]--
	}
	mx, s := 0, 0
	for i, x := range d {
		s += x
		if mx < s {
			mx = s
			ans = base + i
		}
	}
	return
}
```

### **TypeScript**

```ts
function maxAliveYear(birth: number[], death: number[]): number {
    const base = 1900;
    const d: number[] = Array(102).fill(0);
    for (let i = 0; i < birth.length; ++i) {
        const [a, b] = [birth[i] - base, death[i] - base];
        ++d[a];
        --d[b + 1];
    }
    let [s, mx] = [0, 0];
    let ans = 0;
    for (let i = 0; i < d.length; ++i) {
        s += d[i];
        if (mx < s) {
            mx = s;
            ans = base + i;
        }
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_alive_year(birth: Vec<i32>, death: Vec<i32>) -> i32 {
        let n = birth.len();
        let mut d = vec![0; 102];
        let base = 1900;
        for i in 0..n {
            d[(birth[i] - base) as usize] += 1;
            d[(death[i] - base + 1) as usize] -= 1;
        }
        let mut ans = 0;
        let mut mx = 0;
        let mut s = 0;
        for i in 0..102 {
            s += d[i];
            if mx < s {
                mx = s;
                ans = base + (i as i32);
            }
        }
        ans
    }
}
```

### **...**

```

```

<!-- tabs:end -->
