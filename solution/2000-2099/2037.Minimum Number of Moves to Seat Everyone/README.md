---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2037.Minimum%20Number%20of%20Moves%20to%20Seat%20Everyone/README.md
rating: 1356
source: 第 63 场双周赛 Q1
tags:
    - 贪心
    - 数组
    - 计数排序
    - 排序
---

<!-- problem:start -->

# [2037. 使每位学生都有座位的最少移动次数](https://leetcode.cn/problems/minimum-number-of-moves-to-seat-everyone)

[English Version](/solution/2000-2099/2037.Minimum%20Number%20of%20Moves%20to%20Seat%20Everyone/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一个房间里有 <code>n</code>&nbsp;个 <strong>空闲</strong> 座位和 <code>n</code>&nbsp;名 <strong>站着的</strong>&nbsp;学生，房间用一个数轴表示。给你一个长度为 <code>n</code>&nbsp;的数组&nbsp;<code>seats</code>&nbsp;，其中&nbsp;<code>seats[i]</code> 是第 <code>i</code>&nbsp;个座位的位置。同时给你一个长度为 <code>n</code>&nbsp;的数组&nbsp;<code>students</code>&nbsp;，其中&nbsp;<code>students[j]</code>&nbsp;是第 <code>j</code>&nbsp;位学生的位置。</p>

<p>你可以执行以下操作任意次：</p>

<ul>
	<li>增加或者减少第&nbsp;<code>i</code>&nbsp;位学生的位置，每次变化量为 <code>1</code>&nbsp;（也就是将第 <code>i</code>&nbsp;位学生从位置 <code>x</code>&nbsp;移动到 <code>x + 1</code>&nbsp;或者 <code>x - 1</code>）</li>
</ul>

<p>请你返回使所有学生都有座位坐的 <strong>最少移动次数</strong>&nbsp;，并确保没有两位学生的座位相同。</p>

<p>请注意，初始时有可能有多个座位或者多位学生在 <strong>同一</strong>&nbsp;位置。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>seats = [3,1,5], students = [2,7,4]
<b>输出：</b>4
<b>解释：</b>学生移动方式如下：
- 第一位学生从位置 2 移动到位置 1 ，移动 1 次。
- 第二位学生从位置 7 移动到位置 5 ，移动 2 次。
- 第三位学生从位置 4 移动到位置 3 ，移动 1 次。
总共 1 + 2 + 1 = 4 次移动。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>seats = [4,1,5,9], students = [1,3,2,6]
<b>输出：</b>7
<strong>解释：</strong>学生移动方式如下：
- 第一位学生不移动。
- 第二位学生从位置 3 移动到位置 4 ，移动 1 次。
- 第三位学生从位置 2 移动到位置 5 ，移动 3 次。
- 第四位学生从位置 6 移动到位置 9 ，移动 3 次。
总共 0 + 1 + 3 + 3 = 7 次移动。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>seats = [2,2,6,6], students = [1,3,2,6]
<b>输出：</b>4
<b>解释：</b>学生移动方式如下：
- 第一位学生从位置 1 移动到位置 2 ，移动 1 次。
- 第二位学生从位置 3 移动到位置 6 ，移动 3 次。
- 第三位学生不移动。
- 第四位学生不移动。
总共 1 + 3 + 0 + 0 = 4 次移动。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == seats.length == students.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= seats[i], students[j] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

将两个数组分别排序，然后遍历两个数组，计算每个学生的座位与其实际座位的距离，将所有距离相加即为答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 `seats` 和 `students` 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMovesToSeat(self, seats: List[int], students: List[int]) -> int:
        seats.sort()
        students.sort()
        return sum(abs(a - b) for a, b in zip(seats, students))
```

#### Java

```java
class Solution {
    public int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);
        int ans = 0;
        for (int i = 0; i < seats.length; ++i) {
            ans += Math.abs(seats[i] - students[i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minMovesToSeat(vector<int>& seats, vector<int>& students) {
        sort(seats.begin(), seats.end());
        sort(students.begin(), students.end());
        int ans = 0;
        for (int i = 0; i < seats.size(); ++i) {
            ans += abs(seats[i] - students[i]);
        }
        return ans;
    }
};
```

#### Go

```go
func minMovesToSeat(seats []int, students []int) (ans int) {
	sort.Ints(seats)
	sort.Ints(students)
	for i, a := range seats {
		b := students[i]
		ans += abs(a - b)
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function minMovesToSeat(seats: number[], students: number[]): number {
    seats.sort((a, b) => a - b);
    students.sort((a, b) => a - b);
    return seats.reduce((acc, seat, i) => acc + Math.abs(seat - students[i]), 0);
}
```

#### Rust

```rust
impl Solution {
    pub fn min_moves_to_seat(mut seats: Vec<i32>, mut students: Vec<i32>) -> i32 {
        seats.sort();
        students.sort();
        let n = seats.len();
        let mut ans = 0;
        for i in 0..n {
            ans += (seats[i] - students[i]).abs();
        }
        ans
    }
}
```

#### C

```c
int cmp(const void* a, const void* b) {
    return *(int*) a - *(int*) b;
}

int minMovesToSeat(int* seats, int seatsSize, int* students, int studentsSize) {
    qsort(seats, seatsSize, sizeof(int), cmp);
    qsort(students, studentsSize, sizeof(int), cmp);
    int ans = 0;
    for (int i = 0; i < seatsSize; i++) {
        ans += abs(seats[i] - students[i]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
