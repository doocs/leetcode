---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1013.Partition%20Array%20Into%20Three%20Parts%20With%20Equal%20Sum/README.md
rating: 1378
source: 第 129 场周赛 Q1
tags:
    - 贪心
    - 数组
---

<!-- problem:start -->

# [1013. 将数组分成和相等的三个部分](https://leetcode.cn/problems/partition-array-into-three-parts-with-equal-sum)

[English Version](/solution/1000-1099/1013.Partition%20Array%20Into%20Three%20Parts%20With%20Equal%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>arr</code>，只有可以将其划分为三个和相等的 <strong>非空</strong> 部分时才返回 <code>true</code>，否则返回 <code>false</code>。</p>

<p>形式上，如果可以找出索引 <code>i + 1 < j</code> 且满足 <code>(arr[0] + arr[1] + ... + arr[i] == arr[i + 1] + arr[i + 2] + ... + arr[j - 1] == arr[j] + arr[j + 1] + ... + arr[arr.length - 1])</code> 就可以将数组三等分。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [0,2,1,-6,6,-7,9,1,2,0,1]
<strong>输出：</strong>true
<strong>解释：</strong>0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [0,2,1,-6,6,7,9,-1,2,0,1]
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [3,3,6,5,-2,2,5,1,-9,4]
<strong>输出：</strong>true
<strong>解释：</strong>3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 <= arr.length <= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> <= arr[i] <= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历求和

我们先求出整个数组的和，然后判断和是否能被 3 整除，如果不能，直接返回 $\textit{false}$。

否则，我们记 $\textit{s}$ 表示每部分的和，用一个变量 $\textit{cnt}$ 记录当前已经找到的部分数，另一个变量 $\textit{t}$ 记录当前部分的和。初始时 $\textit{cnt} = 0$, $t = 0$。

然后我们遍历数组，对于每个元素 $x$，我们将 $t$ 加上 $x$，如果 $t$ 等于 $s$，说明找到了一部分，将 $\textit{cnt}$ 加一，然后将 $t$ 置为 0。

最后判断 $\textit{cnt}$ 是否大于等于 3 即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{arr}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canThreePartsEqualSum(self, arr: List[int]) -> bool:
        s, mod = divmod(sum(arr), 3)
        if mod:
            return False
        cnt = t = 0
        for x in arr:
            t += x
            if t == s:
                cnt += 1
                t = 0
        return cnt >= 3
```

#### Java

```java
class Solution {
    public boolean canThreePartsEqualSum(int[] arr) {
        int s = Arrays.stream(arr).sum();
        if (s % 3 != 0) {
            return false;
        }
        s /= 3;
        int cnt = 0, t = 0;
        for (int x : arr) {
            t += x;
            if (t == s) {
                cnt++;
                t = 0;
            }
        }
        return cnt >= 3;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canThreePartsEqualSum(vector<int>& arr) {
        int s = accumulate(arr.begin(), arr.end(), 0);
        if (s % 3) {
            return false;
        }
        s /= 3;
        int cnt = 0, t = 0;
        for (int x : arr) {
            t += x;
            if (t == s) {
                t = 0;
                cnt++;
            }
        }
        return cnt >= 3;
    }
};
```

#### Go

```go
func canThreePartsEqualSum(arr []int) bool {
	s := 0
	for _, x := range arr {
		s += x
	}
	if s%3 != 0 {
		return false
	}
	s /= 3
	cnt, t := 0, 0
	for _, x := range arr {
		t += x
		if t == s {
			cnt++
			t = 0
		}
	}
	return cnt >= 3
}
```

#### TypeScript

```ts
function canThreePartsEqualSum(arr: number[]): boolean {
    let s = arr.reduce((a, b) => a + b);
    if (s % 3) {
        return false;
    }
    s = (s / 3) | 0;
    let [cnt, t] = [0, 0];
    for (const x of arr) {
        t += x;
        if (t == s) {
            cnt++;
            t = 0;
        }
    }
    return cnt >= 3;
}
```

#### Rust

```rust
impl Solution {
    pub fn can_three_parts_equal_sum(arr: Vec<i32>) -> bool {
        let sum: i32 = arr.iter().sum();
        let s = sum / 3;
        let mod_val = sum % 3;
        if mod_val != 0 {
            return false;
        }

        let mut cnt = 0;
        let mut t = 0;
        for &x in &arr {
            t += x;
            if t == s {
                cnt += 1;
                t = 0;
            }
        }

        cnt >= 3
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
