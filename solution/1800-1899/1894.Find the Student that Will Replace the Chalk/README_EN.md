---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1894.Find%20the%20Student%20that%20Will%20Replace%20the%20Chalk/README_EN.md
rating: 1355
source: Biweekly Contest 54 Q2
tags:
    - Array
    - Binary Search
    - Prefix Sum
    - Simulation
---

# [1894. Find the Student that Will Replace the Chalk](https://leetcode.com/problems/find-the-student-that-will-replace-the-chalk)

[中文文档](/solution/1800-1899/1894.Find%20the%20Student%20that%20Will%20Replace%20the%20Chalk/README.md)

## Description

<p>There are <code>n</code> students in a class numbered from <code>0</code> to <code>n - 1</code>. The teacher will give each student a problem starting with the student number <code>0</code>, then the student number <code>1</code>, and so on until the teacher reaches the student number <code>n - 1</code>. After that, the teacher will restart the process, starting with the student number <code>0</code> again.</p>

<p>You are given a <strong>0-indexed</strong> integer array <code>chalk</code> and an integer <code>k</code>. There are initially <code>k</code> pieces of chalk. When the student number <code>i</code> is given a problem to solve, they will use <code>chalk[i]</code> pieces of chalk to solve that problem. However, if the current number of chalk pieces is <strong>strictly less</strong> than <code>chalk[i]</code>, then the student number <code>i</code> will be asked to <strong>replace</strong> the chalk.</p>

<p>Return <em>the <strong>index</strong> of the student that will <strong>replace</strong> the chalk pieces</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> chalk = [5,1,5], k = 22
<strong>Output:</strong> 0
<strong>Explanation: </strong>The students go in turns as follows:
- Student number 0 uses 5 chalk, so k = 17.
- Student number 1 uses 1 chalk, so k = 16.
- Student number 2 uses 5 chalk, so k = 11.
- Student number 0 uses 5 chalk, so k = 6.
- Student number 1 uses 1 chalk, so k = 5.
- Student number 2 uses 5 chalk, so k = 0.
Student number 0 does not have enough chalk, so they will have to replace it.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> chalk = [3,4,1,2], k = 25
<strong>Output:</strong> 1
<strong>Explanation: </strong>The students go in turns as follows:
- Student number 0 uses 3 chalk so k = 22.
- Student number 1 uses 4 chalk so k = 18.
- Student number 2 uses 1 chalk so k = 17.
- Student number 3 uses 2 chalk so k = 15.
- Student number 0 uses 3 chalk so k = 12.
- Student number 1 uses 4 chalk so k = 8.
- Student number 2 uses 1 chalk so k = 7.
- Student number 3 uses 2 chalk so k = 5.
- Student number 0 uses 3 chalk so k = 2.
Student number 1 does not have enough chalk, so they will have to replace it.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>chalk.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= chalk[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Sum and Modulo + Simulation

Since the students' answers are conducted in rounds, we can add up the chalk needed by all students to get a total $s$. Then we take the remainder of $k$ by $s$, which can tell us the remaining number of chalks after the last round.

Next, we just need to simulate the last round. Initially, the remaining number of chalks is $k$, and the student with the number $0$ starts to answer the question. When the remaining number of chalks is less than the current student needs, the current student needs to replenish the chalk, and we directly return the current student's number $i$. Otherwise, we subtract the chalk needed by the current student from the remaining chalk, and add one to the current student's number $i$ for the next simulation.

The time complexity is $O(n)$, where $n$ is the number of students. The space complexity is $O(1)$.

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
