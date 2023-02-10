# [2554. 从一个范围内选择最多整数 I](https://leetcode.cn/problems/maximum-number-of-integers-to-choose-from-a-range-i)

[English Version](/solution/2500-2599/2554.Maximum%20Number%20of%20Integers%20to%20Choose%20From%20a%20Range%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>banned</code>&nbsp;和两个整数&nbsp;<code>n</code> 和&nbsp;<code>maxSum</code>&nbsp;。你需要按照以下规则选择一些整数：</p>

<ul>
	<li>被选择整数的范围是&nbsp;<code>[1, n]</code>&nbsp;。</li>
	<li>每个整数 <strong>至多</strong>&nbsp;选择 <strong>一次</strong>&nbsp;。</li>
	<li>被选择整数不能在数组&nbsp;<code>banned</code>&nbsp;中。</li>
	<li>被选择整数的和不超过&nbsp;<code>maxSum</code>&nbsp;。</li>
</ul>

<p>请你返回按照上述规则 <strong>最多</strong>&nbsp;可以选择的整数数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>banned = [1,6,5], n = 5, maxSum = 6
<b>输出：</b>2
<b>解释：</b>你可以选择整数 2 和 4 。
2 和 4 在范围 [1, 5] 内，且它们都不在 banned 中，它们的和是 6 ，没有超过 maxSum 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>banned = [1,2,3,4,5,6,7], n = 8, maxSum = 1
<b>输出：</b>0
<b>解释：</b>按照上述规则无法选择任何整数。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>banned = [11], n = 7, maxSum = 50
<b>输出：</b>7
<b>解释：</b>你可以选择整数 1, 2, 3, 4, 5, 6 和 7 。
它们都在范围 [1, 7] 中，且都没出现在 banned 中，它们的和是 28 ，没有超过 maxSum 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= banned.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= banned[i], n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= maxSum &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 枚举**

我们用变量 $s$ 表示当前已经选择的整数的和，用变量 $ans$ 表示当前已经选择的整数的个数。将数组 `banned` 转换为哈希表，方便判断某个整数是否不可选。

接下来，我们从 $1$ 开始枚举整数 $i$，如果 $s + i \leq maxSum$ 且 $i$ 不在 `banned` 中，那么我们就可以选择整数 $i$，并将 $s$ 和 $ans$ 分别加上 $i$ 和 $1$。

最终，我们返回 $ans$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为给定的整数。

**方法二：贪心 + 二分查找**

如果 $n$ 很大，那么方法一中的枚举会超时。

我们可以在数组 `banned` 中加入 $0$ 和 $n + 1$，并将数组 `banned` 去重且移除大于 $n+1$ 的元素，然后进行排序。

接下来，我们枚举数组 `banned` 中的每两个相邻元素 $i$ 和 $j$，那么可选的整数范围就是 $[i + 1, j - 1]$。二分枚举我们在此区间内能够选择的元素个数，找到最大的可选元素个数，然后将其加到 $ans$ 中。同时在 `maxSum` 中减去这些元素的和。如果 `maxSum` 小于 $0$，那么我们跳出循环。返回答案即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `banned` 的长度。

相似题目：[2557. 从一个范围内选择最多整数 II](/solution/2500-2599/2557.Maximum%20Number%20of%20Integers%20to%20Choose%20From%20a%20Range%20II/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxCount(self, banned: List[int], n: int, maxSum: int) -> int:
        ans = s = 0
        ban = set(banned)
        for i in range(1, n + 1):
            if s + i > maxSum:
                break
            if i not in ban:
                ans += 1
                s += i
        return ans
```

```python
class Solution:
    def maxCount(self, banned: List[int], n: int, maxSum: int) -> int:
        banned.extend([0, n + 1])
        ban = sorted(x for x in set(banned) if x < n + 2)
        ans = 0
        for i, j in pairwise(ban):
            left, right = 0, j - i - 1
            while left < right:
                mid = (left + right + 1) >> 1
                if (i + 1 + i + mid) * mid // 2 <= maxSum:
                    left = mid
                else:
                    right = mid - 1
            ans += left
            maxSum -= (i + 1 + i + left) * left // 2
            if maxSum <= 0:
                break
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxCount(int[] banned, int n, int maxSum) {
        Set<Integer> ban = new HashSet<>(banned.length);
        for (int x : banned) {
            ban.add(x);
        }
        int ans = 0, s = 0;
        for (int i = 1; i <= n && s + i <= maxSum; ++i) {
            if (!ban.contains(i)) {
                ++ans;
                s += i;
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int maxCount(int[] banned, int n, int maxSum) {
        Set<Integer> black = new HashSet<>();
        black.add(0);
        black.add(n + 1);
        for (int x : banned) {
            if (x < n + 2) {
                black.add(x);
            }
        }
        List<Integer> ban = new ArrayList<>(black);
        Collections.sort(ban);
        int ans = 0;
        for (int k = 1; k < ban.size(); ++k) {
            int i = ban.get(k - 1), j = ban.get(k);
            int left = 0, right = j - i - 1;
            while (left < right) {
                int mid = (left + right + 1) >>> 1;
                if ((i + 1 + i + mid) * 1L * mid / 2 <= maxSum) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            ans += left;
            maxSum -= (i + 1 + i + left) * 1L * left / 2;
            if (maxSum <= 0) {
                break;
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
    int maxCount(vector<int>& banned, int n, int maxSum) {
        unordered_set<int> ban(banned.begin(), banned.end());
        int ans = 0, s = 0;
        for (int i = 1; i <= n && s + i <= maxSum; ++i) {
            if (!ban.count(i)) {
                ++ans;
                s += i;
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int maxCount(vector<int>& banned, int n, int maxSum) {
        banned.push_back(0);
        banned.push_back(n + 1);
        sort(banned.begin(), banned.end());
        banned.erase(unique(banned.begin(), banned.end()), banned.end());
        banned.erase(remove_if(banned.begin(), banned.end(), [&](int x) { return x > n + 1; }), banned.end());
        int ans = 0;
        for (int k = 1; k < banned.size(); ++k) {
            int i = banned[k - 1], j = banned[k];
            int left = 0, right = j - i - 1;
            while (left < right) {
                int mid = left + ((right - left + 1) / 2);
                if ((i + 1 + i + mid) * 1LL * mid / 2 <= maxSum) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            ans += left;
            maxSum -= (i + 1 + i + left) * 1LL * left / 2;
            if (maxSum <= 0) {
                break;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxCount(banned []int, n int, maxSum int) (ans int) {
	ban := map[int]bool{}
	for _, x := range banned {
		ban[x] = true
	}
	s := 0
	for i := 1; i <= n && s+i <= maxSum; i++ {
		if !ban[i] {
			ans++
			s += i
		}
	}
	return
}
```

```go
func maxCount(banned []int, n int, maxSum int) (ans int) {
	banned = append(banned, []int{0, n + 1}...)
	sort.Ints(banned)
	ban := []int{}
	for i, x := range banned {
		if (i > 0 && x == banned[i-1]) || x > n+1 {
			continue
		}
		ban = append(ban, x)
	}
	for k := 1; k < len(ban); k++ {
		i, j := ban[k-1], ban[k]
		left, right := 0, j-i-1
		for left < right {
			mid := (left + right + 1) >> 1
			if (i+1+i+mid)*mid/2 <= maxSum {
				left = mid
			} else {
				right = mid - 1
			}
		}
		ans += left
		maxSum -= (i + 1 + i + left) * left / 2
		if maxSum <= 0 {
			break
		}
	}
	return
}
```

### **TypeScript**

```ts
function maxCount(banned: number[], n: number, maxSum: number): number {
    const set = new Set(banned);
    let sum = 0;
    let ans = 0;
    for (let i = 1; i <= n; i++) {
        if (i + sum > maxSum) {
            break;
        }
        if (set.has(i)) {
            continue;
        }
        sum += i;
        ans++;
    }
    return ans;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    pub fn max_count(banned: Vec<i32>, n: i32, max_sum: i32) -> i32 {
        let mut set = banned.into_iter().collect::<HashSet<i32>>();
        let mut sum = 0;
        let mut ans = 0;
        for i in 1..=n {
            if sum + i > max_sum {
                break;
            }
            if set.contains(&i) {
                continue;
            }
            sum += i;
            ans += 1;
        }
        ans
    }
}
```

### **C**

```c
int cmp(const void *a, const void *b) {
    return *(int *) a - *(int *) b;
}

int maxCount(int *banned, int bannedSize, int n, int maxSum) {
    qsort(banned, bannedSize, sizeof(int), cmp);
    int sum = 0;
    int ans = 0;
    for (int i = 1, j = 0; i <= n; i++) {
        if (sum + i > maxSum) {
            break;
        }
        if (j < bannedSize && i == banned[j]) {
            while (j < bannedSize && i == banned[j]) {
                j++;
            }
        } else {
            sum += i;
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
