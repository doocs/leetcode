# [1710. 卡车上的最大单元数](https://leetcode.cn/problems/maximum-units-on-a-truck)

[English Version](/solution/1700-1799/1710.Maximum%20Units%20on%20a%20Truck/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你将一些箱子装在 <strong>一辆卡车</strong> 上。给你一个二维数组 <code>boxTypes</code> ，其中 <code>boxTypes[i] = [numberOfBoxes<sub>i</sub>, numberOfUnitsPerBox<sub>i</sub>]</code> ：</p>

<ul>
	<li><code>numberOfBoxes<sub>i</sub></code> 是类型 <code>i</code> 的箱子的数量。</li>
	<li><code>numberOfUnitsPerBox<sub>i</sub></code><sub> </sub>是类型 <code>i</code> 每个箱子可以装载的单元数量。</li>
</ul>

<p>整数 <code>truckSize</code> 表示卡车上可以装载 <strong>箱子</strong> 的 <strong>最大数量</strong> 。只要箱子数量不超过 <code>truckSize</code> ，你就可以选择任意箱子装到卡车上。</p>

<p>返回卡车可以装载 <strong>单元</strong> 的 <strong>最大</strong> 总数<em>。</em></p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
<strong>输出：</strong>8
<strong>解释：</strong>箱子的情况如下：
- 1 个第一类的箱子，里面含 3 个单元。
- 2 个第二类的箱子，每个里面含 2 个单元。
- 3 个第三类的箱子，每个里面含 1 个单元。
可以选择第一类和第二类的所有箱子，以及第三类的一个箱子。
单元总数 = (1 * 3) + (2 * 2) + (1 * 1) = 8</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
<strong>输出：</strong>91
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= boxTypes.length <= 1000</code></li>
	<li><code>1 <= numberOfBoxes<sub>i</sub>, numberOfUnitsPerBox<sub>i</sub> <= 1000</code></li>
	<li><code>1 <= truckSize <= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 排序**

根据题意，我们应该选择尽可能多的单元数，因此，我们先对 `boxTypes` 按照单元数从大到小的顺序排列。

然后从前往后遍历 `boxTypes`，选择最多 `truckSize` 个箱子，累加单元数。

时间复杂度 $O(n\times \log n)$，其中 $n$ 表示二维数组 `boxTypes` 的长度。

**方法二：计数排序**

我们还可以利用计数排序的思想，开一个长度为 $1001$ 的数组 $cnt$，其中 $cnt[b]$ 表示单元数为 $b$ 的箱子的数量。

然后从单元数最大的箱子开始，选择最多 `truckSize` 个箱子，累加单元数。

时间复杂度 $O(M)$，其中 $M$ 是单元数的最大值。本题中 $M=1000$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumUnits(self, boxTypes: List[List[int]], truckSize: int) -> int:
        ans = 0
        for a, b in sorted(boxTypes, key=lambda x: -x[1]):
            ans += b * min(truckSize, a)
            truckSize -= a
            if truckSize <= 0:
                break
        return ans
```

```python
class Solution:
    def maximumUnits(self, boxTypes: List[List[int]], truckSize: int) -> int:
        cnt = [0] * 1001
        for a, b in boxTypes:
            cnt[b] += a
        ans = 0
        for b in range(1000, 0, -1):
            a = cnt[b]
            if a:
                ans += b * min(truckSize, a)
                truckSize -= a
                if truckSize <= 0:
                    break
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int ans = 0;
        for (var e : boxTypes) {
            int a = e[0], b = e[1];
            ans += b * Math.min(truckSize, a);
            truckSize -= a;
            if (truckSize <= 0) {
                break;
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int[] cnt = new int[1001];
        for (var e : boxTypes) {
            int a = e[0], b = e[1];
            cnt[b] += a;
        }
        int ans = 0;
        for (int b = 1000; b > 0 && truckSize > 0; --b) {
            int a = cnt[b];
            if (a > 0) {
                ans += b * Math.min(truckSize, a);
                truckSize -= a;
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
    int maximumUnits(vector<vector<int>>& boxTypes, int truckSize) {
        sort(boxTypes.begin(), boxTypes.end(), [](auto& a, auto& b) { return a[1] > b[1]; });
        int ans = 0;
        for (auto& e : boxTypes) {
            int a = e[0], b = e[1];
            ans += b * min(truckSize, a);
            truckSize -= a;
            if (truckSize <= 0) break;
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int maximumUnits(vector<vector<int>>& boxTypes, int truckSize) {
        int cnt[1001] = {0};
        for (auto& e : boxTypes) {
            int a = e[0], b = e[1];
            cnt[b] += a;
        }
        int ans = 0;
        for (int b = 1000; b > 0 && truckSize > 0; --b) {
            int a = cnt[b];
            if (a) {
                ans += b * min(truckSize, a);
                truckSize -= a;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumUnits(boxTypes [][]int, truckSize int) (ans int) {
	sort.Slice(boxTypes, func(i, j int) bool { return boxTypes[i][1] > boxTypes[j][1] })
	for _, e := range boxTypes {
		a, b := e[0], e[1]
		ans += b * min(truckSize, a)
		truckSize -= a
		if truckSize <= 0 {
			break
		}
	}
	return
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

```go
func maximumUnits(boxTypes [][]int, truckSize int) (ans int) {
	cnt := [1001]int{}
	for _, e := range boxTypes {
		a, b := e[0], e[1]
		cnt[b] += a
	}
	for b := 1000; b > 0 && truckSize > 0; b-- {
		a := cnt[b]
		if a > 0 {
			ans += b * min(truckSize, a)
			truckSize -= a
		}
	}
	return
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function maximumUnits(boxTypes: number[][], truckSize: number): number {
    boxTypes.sort((a, b) => b[1] - a[1]);
    let sum = 0;
    let ans = 0;
    for (const [count, size] of boxTypes) {
        if (sum + count < truckSize) {
            ans += size * count;
            sum += count;
        } else {
            ans += (truckSize - sum) * size;
            break;
        }
    }
    return ans;
}
```

```ts
function maximumUnits(boxTypes: number[][], truckSize: number): number {
    const cnt = new Array(1001).fill(0);
    for (const [a, b] of boxTypes) {
        cnt[b] += a;
    }
    let ans = 0;
    for (let b = 1000; b > 0 && truckSize > 0; --b) {
        const a = cnt[b];
        if (a > 0) {
            ans += b * Math.min(truckSize, a);
            truckSize -= a;
        }
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn maximum_units(mut box_types: Vec<Vec<i32>>, truck_size: i32) -> i32 {
        box_types.sort_by(|a, b| b[1].cmp(&a[1]));
        let mut sum = 0;
        let mut ans = 0;
        for box_type in box_types.iter() {
            if sum + box_type[0] < truck_size {
                sum += box_type[0];
                ans += box_type[0] * box_type[1];
            } else {
                ans += (truck_size - sum) * box_type[1];
                break;
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
