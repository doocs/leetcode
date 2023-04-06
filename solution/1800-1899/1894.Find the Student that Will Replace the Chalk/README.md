# [1894. 找到需要补充粉笔的学生编号](https://leetcode.cn/problems/find-the-student-that-will-replace-the-chalk)

[English Version](/solution/1800-1899/1894.Find%20the%20Student%20that%20Will%20Replace%20the%20Chalk/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个班级里有&nbsp;<code>n</code>&nbsp;个学生，编号为 <code>0</code>&nbsp;到 <code>n - 1</code>&nbsp;。每个学生会依次回答问题，编号为 <code>0</code>&nbsp;的学生先回答，然后是编号为 <code>1</code>&nbsp;的学生，以此类推，直到编号为 <code>n - 1</code>&nbsp;的学生，然后老师会重复这个过程，重新从编号为 <code>0</code>&nbsp;的学生开始回答问题。</p>

<p>给你一个长度为 <code>n</code>&nbsp;且下标从 <code>0</code>&nbsp;开始的整数数组&nbsp;<code>chalk</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。一开始粉笔盒里总共有&nbsp;<code>k</code>&nbsp;支粉笔。当编号为&nbsp;<code>i</code>&nbsp;的学生回答问题时，他会消耗 <code>chalk[i]</code>&nbsp;支粉笔。如果剩余粉笔数量 <strong>严格小于</strong>&nbsp;<code>chalk[i]</code>&nbsp;，那么学生 <code>i</code>&nbsp;需要 <strong>补充</strong>&nbsp;粉笔。</p>

<p>请你返回需要 <strong>补充</strong>&nbsp;粉笔的学生 <strong>编号</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>chalk = [5,1,5], k = 22
<b>输出：</b>0
<strong>解释：</strong>学生消耗粉笔情况如下：
- 编号为 0 的学生使用 5 支粉笔，然后 k = 17 。
- 编号为 1 的学生使用 1 支粉笔，然后 k = 16 。
- 编号为 2 的学生使用 5 支粉笔，然后 k = 11 。
- 编号为 0 的学生使用 5 支粉笔，然后 k = 6 。
- 编号为 1 的学生使用 1 支粉笔，然后 k = 5 。
- 编号为 2 的学生使用 5 支粉笔，然后 k = 0 。
编号为 0 的学生没有足够的粉笔，所以他需要补充粉笔。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>chalk = [3,4,1,2], k = 25
<b>输出：</b>1
<b>解释：</b>学生消耗粉笔情况如下：
- 编号为 0 的学生使用 3 支粉笔，然后 k = 22 。
- 编号为 1 的学生使用 4 支粉笔，然后 k = 18 。
- 编号为 2 的学生使用 1 支粉笔，然后 k = 17 。
- 编号为 3 的学生使用 2 支粉笔，然后 k = 15 。
- 编号为 0 的学生使用 3 支粉笔，然后 k = 12 。
- 编号为 1 的学生使用 4 支粉笔，然后 k = 8 。
- 编号为 2 的学生使用 1 支粉笔，然后 k = 7 。
- 编号为 3 的学生使用 2 支粉笔，然后 k = 5 。
- 编号为 0 的学生使用 3 支粉笔，然后 k = 2 。
编号为 1 的学生没有足够的粉笔，所以他需要补充粉笔。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>chalk.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= chalk[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和 + 二分查找**

以下是二分查找的两个通用模板：

模板 1：

```java
boolean check(int x) {}

int search(int left, int right) {
    while (left < right) {
        int mid = (left + right) >> 1;
        if (check(mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

模板 2：

```java
boolean check(int x) {}

int search(int left, int right) {
    while (left < right) {
        int mid = (left + right + 1) >> 1;
        if (check(mid)) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
}
```

做二分题目时，可以按照以下步骤：

1. 写出循环条件：`while (left < right)`，注意是 `left < right`，而非 `left <= right`；
1. 循环体内，先无脑写出 `mid = (left + right) >> 1`；
1. 根据具体题目，实现 `check()` 函数（有时很简单的逻辑，可以不定义 `check`），想一下究竟要用 `right = mid`（模板 1） 还是 `left = mid`（模板 2）；
    - 如果 `right = mid`，那么无脑写出 else 语句 `left = mid + 1`，并且不需要更改 mid 的计算，即保持 `mid = (left + right) >> 1`；
    - 如果 `left = mid`，那么无脑写出 else 语句 `right = mid - 1`，并且在 mid 计算时补充 +1，即 `mid = (left + right + 1) >> 1`。
1. 循环结束时，left 与 right 相等。

注意，这两个模板的优点是始终保持答案位于二分区间内，二分结束条件对应的值恰好在答案所处的位置。 对于可能无解的情况，只要判断二分结束后的 left 或者 right 是否满足题意即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def chalkReplacer(self, chalk: List[int], k: int) -> int:
        s = list(accumulate(chalk))
        k %= s[-1]
        return bisect_right(s, k)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int chalkReplacer(int[] chalk, int k) {
        int n = chalk.length;
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            preSum[i + 1] = preSum[i] + chalk[i];
        }
        k %= preSum[n];
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (preSum[mid + 1] > k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int chalkReplacer(vector<int>& chalk, int k) {
        int n = chalk.size();
        vector<long long> s(n, chalk[0]);
        for (int i = 1; i < n; ++i) s[i] = s[i - 1] + chalk[i];
        k %= s[n - 1];
        return upper_bound(s.begin(), s.end(), k) - s.begin();
    }
};
```

### **Go**

```go
func chalkReplacer(chalk []int, k int) int {
	n := len(chalk)
	s := make([]int, n+1)
	for i := 0; i < n; i++ {
		s[i+1] = s[i] + chalk[i]
	}
	k %= s[n]
	return sort.Search(n, func(i int) bool { return s[i+1] > k })
}
```

### **Rust**

```rust
impl Solution {
    pub fn chalk_replacer(chalk: Vec<i32>, k: i32) -> i32 {
        let pre_sum: Vec<i64> = chalk
            .into_iter()
            .map(|x| x as i64)
            .scan(0, |state, x| {
                *state += x;
                Some(*state)
            })
            .collect();

        pre_sum
            .binary_search(&(k as i64 % pre_sum.last().unwrap()))
            .map_or_else(|e| e, |v| v + 1) as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
