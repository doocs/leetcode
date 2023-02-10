# [2335. 装满杯子需要的最短总时长](https://leetcode.cn/problems/minimum-amount-of-time-to-fill-cups)

[English Version](/solution/2300-2399/2335.Minimum%20Amount%20of%20Time%20to%20Fill%20Cups/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现有一台饮水机，可以制备冷水、温水和热水。每秒钟，可以装满 <code>2</code> 杯 <strong>不同</strong> 类型的水或者 <code>1</code> 杯任意类型的水。</p>

<p>给你一个下标从 <strong>0</strong> 开始、长度为 <code>3</code> 的整数数组 <code>amount</code> ，其中 <code>amount[0]</code>、<code>amount[1]</code> 和 <code>amount[2]</code> 分别表示需要装满冷水、温水和热水的杯子数量。返回装满所有杯子所需的 <strong>最少</strong> 秒数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>amount = [1,4,2]
<strong>输出：</strong>4
<strong>解释：</strong>下面给出一种方案：
第 1 秒：装满一杯冷水和一杯温水。
第 2 秒：装满一杯温水和一杯热水。
第 3 秒：装满一杯温水和一杯热水。
第 4 秒：装满一杯温水。
可以证明最少需要 4 秒才能装满所有杯子。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>amount = [5,4,4]
<strong>输出：</strong>7
<strong>解释：</strong>下面给出一种方案：
第 1 秒：装满一杯冷水和一杯热水。
第 2 秒：装满一杯冷水和一杯温水。
第 3 秒：装满一杯冷水和一杯温水。
第 4 秒：装满一杯温水和一杯热水。
第 5 秒：装满一杯冷水和一杯热水。
第 6 秒：装满一杯冷水和一杯温水。
第 7 秒：装满一杯热水。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>amount = [5,0,0]
<strong>输出：</strong>5
<strong>解释：</strong>每秒装满一杯冷水。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>amount.length == 3</code></li>
	<li><code>0 &lt;= amount[i] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 排序**

我们可以每次贪心地选择其中较大的两个数进行减一操作（最多减为 $0$），直至所有数变为 $0$。

时间复杂度 $O(S)$，空间复杂度 $O(1)$。其中 $S$ 为数组 `amount` 中所有数的和，本题中 $S \leq 300$。

**方法二：贪心 + 分类讨论**

我们可以将数组 `amount` 排序，设 $a$, $b$, $c$ 分别为数组 `amount` 中的三个数，有以下两种情况：

-   如果 $a + b \leq c$，此时我们只需要 $c$ 次操作即可将所有数变为 $0$，因此答案为 $c$。
-   如果 $a + b > c$，每一次操作我们都可以将其中两个数减一，最终匹配完，或者剩下最后一个数（取决于总和是偶数还是奇数），因此答案为 $\left \lfloor \frac{a + b + c + 1}{2}  \right \rfloor$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def fillCups(self, amount: List[int]) -> int:
        ans = 0
        while sum(amount):
            amount.sort()
            ans += 1
            amount[2] -= 1
            amount[1] = max(0, amount[1] - 1)
        return ans
```

```python
class Solution:
    def fillCups(self, amount: List[int]) -> int:
        amount.sort()
        if amount[0] + amount[1] <= amount[2]:
            return amount[2]
        return (sum(amount) + 1) // 2
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int fillCups(int[] amount) {
        int ans = 0;
        while (amount[0] + amount[1] + amount[2] > 0) {
            Arrays.sort(amount);
            ++ans;
            amount[2]--;
            amount[1] = Math.max(0, amount[1] - 1);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int fillCups(int[] amount) {
        Arrays.sort(amount);
        if (amount[0] + amount[1] <= amount[2]) {
            return amount[2];
        }
        return (amount[0] + amount[1] + amount[2] + 1) / 2;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int fillCups(vector<int>& amount) {
        int ans = 0;
        while (amount[0] + amount[1] + amount[2]) {
            sort(amount.begin(), amount.end());
            ++ans;
            amount[2]--;
            amount[1] = max(0, amount[1] - 1);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int fillCups(vector<int>& amount) {
        sort(amount.begin(), amount.end());
        if (amount[0] + amount[1] <= amount[2]) {
            return amount[2];
        }
        return (amount[0] + amount[1] + amount[2] + 1) / 2;
    }
};
```

### **Go**

```go
func fillCups(amount []int) int {
	ans := 0
	for amount[0]+amount[1]+amount[2] > 0 {
		sort.Ints(amount)
		ans++
		amount[2]--
		if amount[1] > 0 {
			amount[1]--
		}
	}
	return ans
}
```

```go
func fillCups(amount []int) int {
	sort.Ints(amount)
	if amount[0]+amount[1] <= amount[2] {
		return amount[2]
	}
	return (amount[0] + amount[1] + amount[2] + 1) / 2
}
```

### **TypeScript**

```ts
function fillCups(amount: number[]): number {
    amount.sort((a, b) => a - b);
    let [a, b, c] = amount;
    let diff = a + b - c;
    if (diff <= 0) return c;
    else return Math.floor((diff + 1) / 2) + c;
}
```

### **Rust**

```rust
impl Solution {
    pub fn fill_cups(mut amount: Vec<i32>) -> i32 {
        amount.sort();
        let dif = amount[0] + amount[1] - amount[2];
        if dif <= 0 {
            return amount[2];
        }
        (dif + 1) / 2 + amount[2]
    }
}

```

### **...**

```

```

<!-- tabs:end -->
