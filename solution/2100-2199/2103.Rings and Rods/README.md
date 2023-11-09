# [2103. 环和杆](https://leetcode.cn/problems/rings-and-rods)

[English Version](/solution/2100-2199/2103.Rings%20and%20Rods/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>总计有 <code>n</code> 个环，环的颜色可以是红、绿、蓝中的一种。这些环分别穿在 10 根编号为 <code>0</code> 到 <code>9</code> 的杆上。</p>

<p>给你一个长度为 <code>2n</code> 的字符串 <code>rings</code> ，表示这 <code>n</code> 个环在杆上的分布。<code>rings</code> 中每两个字符形成一个 <strong>颜色位置对</strong> ，用于描述每个环：</p>

<ul>
	<li>第 <code>i</code> 对中的 <strong>第一个</strong> 字符表示第 <code>i</code> 个环的 <strong>颜色</strong>（<code>'R'</code>、<code>'G'</code>、<code>'B'</code>）。</li>
	<li>第 <code>i</code> 对中的 <strong>第二个</strong> 字符表示第 <code>i</code> 个环的 <strong>位置</strong>，也就是位于哪根杆上（<code>'0'</code> 到 <code>'9'</code>）。</li>
</ul>

<p>例如，<code>"R3G2B1"</code> 表示：共有 <code>n == 3</code> 个环，红色的环在编号为 3 的杆上，绿色的环在编号为 2 的杆上，蓝色的环在编号为 1 的杆上。</p>

<p>找出所有集齐 <strong>全部三种颜色</strong> 环的杆，并返回这种杆的数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2103.Rings%20and%20Rods/images/ex1final.png" style="width: 258px; height: 130px;" />
<pre>
<strong>输入：</strong>rings = "B0B6G0R6R0R6G9"
<strong>输出：</strong>1
<strong>解释：</strong>
- 编号 0 的杆上有 3 个环，集齐全部颜色：红、绿、蓝。
- 编号 6 的杆上有 3 个环，但只有红、蓝两种颜色。
- 编号 9 的杆上只有 1 个绿色环。
因此，集齐全部三种颜色环的杆的数目为 1 。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2103.Rings%20and%20Rods/images/ex2final.png" style="width: 266px; height: 130px;" />
<pre>
<strong>输入：</strong>rings = "B0R0G0R9R0B0G0"
<strong>输出：</strong>1
<strong>解释：</strong>
- 编号 0 的杆上有 6 个环，集齐全部颜色：红、绿、蓝。
- 编号 9 的杆上只有 1 个红色环。
因此，集齐全部三种颜色环的杆的数目为 1 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>rings = "G4"
<strong>输出：</strong>0
<strong>解释：</strong>
只给了一个环，因此，不存在集齐全部三种颜色环的杆。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>rings.length == 2 * n</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li>如 <code>i</code> 是 <strong>偶数</strong> ，则&nbsp;<code>rings[i]</code> 的值可以取 <code>'R'</code>、<code>'G'</code> 或 <code>'B'</code>（下标从 <strong>0</strong> 开始计数）</li>
	<li>如 <code>i</code> 是 <strong>奇数</strong> ，则&nbsp;<code>rings[i]</code> 的值可以取 <code>'0'</code> 到 <code>'9'</code> 中的一个数字（下标从 <strong>0</strong> 开始计数）</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：位运算**

我们可以用一个长度为 $10$ 的数组 $mask$ 来表示每根杆上的环的颜色情况，其中 $mask[i]$ 表示第 $i$ 根杆上的环的颜色情况，如果第 $i$ 根杆上有红色、绿色、蓝色的环，那么 $mask[i]$ 的二进制表示为 $111$，即 $mask[i] = 7$。

我们遍历字符串 $rings$，对于每个颜色位置对 $(c, j)$，其中 $c$ 表示环的颜色，$j$ 表示环所在的杆的编号，我们将 $mask[j]$ 对应的二进制位进行置位，即 $mask[j] |= d[c]$，其中 $d[c]$ 表示颜色 $c$ 对应的二进制位。

最后我们统计 $mask$ 中值为 $7$ 的元素的个数，即为集齐全部三种颜色环的杆的数目。

时间复杂度 $O(n)$，空间复杂度 $O(|\Sigma|)$，其中 $n$ 表示字符串 $rings$ 的长度，而 $|\Sigma|$ 表示字符集的大小。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countPoints(self, rings: str) -> int:
        mask = [0] * 10
        d = {"R": 1, "G": 2, "B": 4}
        for i in range(0, len(rings), 2):
            c = rings[i]
            j = int(rings[i + 1])
            mask[j] |= d[c]
        return mask.count(7)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countPoints(String rings) {
        int[] d = new int['Z'];
        d['R'] = 1;
        d['G'] = 2;
        d['B'] = 4;
        int[] mask = new int[10];
        for (int i = 0, n = rings.length(); i < n; i += 2) {
            int c = rings.charAt(i);
            int j = rings.charAt(i + 1) - '0';
            mask[j] |= d[c];
        }
        int ans = 0;
        for (int x : mask) {
            if (x == 7) {
                ++ans;
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
    int countPoints(string rings) {
        int d['Z']{['R'] = 1, ['G'] = 2, ['B'] = 4};
        int mask[10]{};
        for (int i = 0, n = rings.size(); i < n; i += 2) {
            int c = rings[i];
            int j = rings[i + 1] - '0';
            mask[j] |= d[c];
        }
        return count(mask, mask + 10, 7);
    }
};
```

### **Go**

```go
func countPoints(rings string) (ans int) {
	d := ['Z']int{'R': 1, 'G': 2, 'B': 4}
	mask := [10]int{}
	for i, n := 0, len(rings); i < n; i += 2 {
		c := rings[i]
		j := int(rings[i+1] - '0')
		mask[j] |= d[c]
	}
	for _, x := range mask {
		if x == 7 {
			ans++
		}
	}
	return
}
```

### **TypeScript**

```ts
function countPoints(rings: string): number {
    const idx = (c: string) => c.charCodeAt(0) - 'A'.charCodeAt(0);
    const d: number[] = Array(26).fill(0);
    d[idx('R')] = 1;
    d[idx('G')] = 2;
    d[idx('B')] = 4;
    const mask: number[] = Array(10).fill(0);
    for (let i = 0; i < rings.length; i += 2) {
        const c = rings[i];
        const j = rings[i + 1].charCodeAt(0) - '0'.charCodeAt(0);
        mask[j] |= d[idx(c)];
    }
    return mask.filter(x => x === 7).length;
}
```

```ts
function countPoints(rings: string): number {
    let c = 0;
    for (let i = 0; i <= 9; i++) {
        if (rings.includes('B' + i) && rings.includes('R' + i) && rings.includes('G' + i)) c++;
    }
    return c;
}
```

### **Rust**

```rust
impl Solution {
    pub fn count_points(rings: String) -> i32 {
        let mut d: [i32; 90] = [0; 90];
        d['R' as usize] = 1;
        d['G' as usize] = 2;
        d['B' as usize] = 4;

        let mut mask: [i32; 10] = [0; 10];

        let cs: Vec<char> = rings.chars().collect();

        for i in (0..cs.len()).step_by(2) {
            let c = cs[i] as usize;
            let j = (cs[i + 1] as usize) - ('0' as usize);
            mask[j] |= d[c];
        }

        mask
            .iter()
            .filter(|&&x| x == 7)
            .count() as i32
    }
}
```

### **C**

```c
int countPoints(char* rings) {
    int d['Z'];
    memset(d, 0, sizeof(d));
    d['R'] = 1;
    d['G'] = 2;
    d['B'] = 4;

    int mask[10];
    memset(mask, 0, sizeof(mask));

    for (int i = 0, n = strlen(rings); i < n; i += 2) {
        int c = rings[i];
        int j = rings[i + 1] - '0';
        mask[j] |= d[c];
    }

    int ans = 0;
    for (int i = 0; i < 10; i++) {
        if (mask[i] == 7) {
            ans++;
        }
    }

    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
