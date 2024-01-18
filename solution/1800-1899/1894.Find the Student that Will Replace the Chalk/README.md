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

### 方法一：求和取余 + 模拟

由于学生的回答是一轮一轮循环进行的，因此我们可以将所有学生需要消耗的粉笔数加起来，得到一个总数 $s$。然后我们对 $k$ 取 $s$ 的余数，即可知道最后一轮结束后剩余的粉笔数。

接下来，我们只需要模拟最后一轮即可。初始时，剩余的粉笔数为 $k$，编号为 $0$ 的学生开始回答问题。当剩余的粉笔数小于当前学生需要的粉笔数时，当前学生需要补充粉笔，我们直接返回当前学生的编号 $i$ 即可。否则，我们将剩余的粉笔数减去当前学生需要的粉笔数，并将当前学生的编号 $i$ 加一，进行下一次模拟。

时间复杂度 $O(n)$，其中 $n$ 是学生的数量。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def chalkReplacer(self, chalk: List[int], k: int) -> int:
        s = sum(chalk)
        k %= s
        for i, x in enumerate(chalk):
            if k < x:
                return i
            k -= x
```

```java
class Solution {
    public int chalkReplacer(int[] chalk, int k) {
        long s = 0;
        for (int x : chalk) {
            s += x;
        }
        k %= s;
        for (int i = 0;; ++i) {
            if (k < chalk[i]) {
                return i;
            }
            k -= chalk[i];
        }
    }
}
```

```cpp
class Solution {
public:
    int chalkReplacer(vector<int>& chalk, int k) {
        long long s = accumulate(chalk.begin(), chalk.end(), 0LL);
        k %= s;
        for (int i = 0;; ++i) {
            if (k < chalk[i]) {
                return i;
            }
            k -= chalk[i];
        }
    }
};
```

```go
func chalkReplacer(chalk []int, k int) int {
	s := 0
	for _, x := range chalk {
		s += x
	}
	k %= s
	for i := 0; ; i++ {
		if k < chalk[i] {
			return i
		}
		k -= chalk[i]
	}
}
```

```ts
function chalkReplacer(chalk: number[], k: number): number {
    let s = 0;
    for (const x of chalk) {
        s += x;
    }
    k %= s;
    for (let i = 0; ; ++i) {
        if (k < chalk[i]) {
            return i;
        }
        k -= chalk[i];
    }
}
```

```rust
impl Solution {
    pub fn chalk_replacer(chalk: Vec<i32>, k: i32) -> i32 {
        let mut s: i64 = chalk
            .iter()
            .map(|&x| x as i64)
            .sum();
        let mut k = (k as i64) % s;
        for (i, &x) in chalk.iter().enumerate() {
            if k < (x as i64) {
                return i as i32;
            }
            k -= x as i64;
        }
        0
    }
}
```

<!-- tabs:end -->

<!-- end -->
